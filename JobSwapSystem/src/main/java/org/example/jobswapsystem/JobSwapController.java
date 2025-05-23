package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Service.MatchService;
import org.example.jobswapsystem.Service.UserService;
import org.example.jobswapsystem.util.SqlConnection;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class JobSwapController {
    MatchService matchService = new MatchService();
    User currentUser = new User();
    MenuCreater menu = new MenuCreater();
    UserService userService = new UserService();
    BorderPane root = menu.root;

    @FXML TextField emailTextField, passwordTextField;



    @FXML GridPane startScreen;


    @FXML
    public void initialize() {
        handleHomeReturn();
        handleSearch();
        handleConfirmSwap();
        handleSendInvitation();
    }
    public void handleSearch(){
        menu.searchLbl.setOnMouseClicked(e -> root.getChildren().clear());
        menu.searchLbl.setOnMouseClicked(e -> menu.topMenu());
        menu.searchLbl.setOnMouseClicked(e -> menu.searchScreen());
    }
    public void handleConfirmSwap(){

    }
    public void handleSendInvitation(){

    }
    public void handleHomeReturn(){
        menu.homeLbl.setOnMouseClicked(e -> root.getChildren().clear());
        menu.homeLbl.setOnMouseClicked(e -> menu.createHomeScreen());
    }
    @FXML
    public void onActionloginButton() {
        User loggedInUser = userService.login(emailTextField.getText(), passwordTextField.getText());
        if (loggedInUser != null) {
            this.currentUser = loggedInUser;

            startScreen.getChildren().clear();
            startScreen.getChildren().add(root);
            loggedInUser = userService.getUserDetails(loggedInUser);
            menu.setCurrentUser(loggedInUser);
            menu.createHomeScreen();


        } else {
            System.out.println("Wrong credentials");
        }
    }


    @FXML
    public void registerButton() {
        // Opret et nyt vindue
        Stage popupStage = new Stage();
        popupStage.setTitle("Registrer ny bruger");

        // Opret inputfelter
        TextField emailInput = new TextField();
        emailInput.setPromptText("Email");

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Adgangskode");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");

        TextField companyInput = new TextField();
        companyInput.setPromptText("Company");

        ComboBox<String> jobTitleCB = new ComboBox<>();
        jobTitleCB.getItems().addAll("Developer", "Designer", "Manager");

        ComboBox<String> areaCodeCB = new ComboBox<>();
        areaCodeCB.getItems().addAll("1000", "2000", "3000");
        areaCodeCB.setValue("1000");

        TextField addressInput = new TextField();
        addressInput.setPromptText("Address");

        Button registerBtn = new Button("Registrer");
        Label statusLabel = new Label();

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(new Label("Email:"), emailInput,
                new Label("Adgangskode:"), passwordInput, new Label("Name:"), nameInput,
                new Label("Address:"), addressInput, new Label("Post kode:"), areaCodeCB,
                new Label("Company:"), companyInput, new Label("Job title:"), jobTitleCB,
                registerBtn, statusLabel);

        // Vis vinduet
        popupStage.setScene(new Scene(layout, 400, 500));
        popupStage.initModality(Modality.APPLICATION_MODAL); // Blokerer hovedvinduet
        popupStage.showAndWait();
    }



}
