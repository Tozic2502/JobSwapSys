package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.example.jobswapsystem.Models.Address;
import org.example.jobswapsystem.Models.Company;
import org.example.jobswapsystem.Models.Position;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Service.*;
import org.example.jobswapsystem.util.SqlConnection;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.List;

public class JobSwapController {
    MatchService matchService = new MatchService();
    IPositionService positionService = new PositionSerice();
    ICompanyService companyService = new CompanyService();
    User currentUser = new User();
    MenuCreater menu = new MenuCreater();
    UserService userService = new UserService();
    BorderPane root = menu.root;
    IUserService userServiceinterface = new UserService();

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
            userService.getJobTitleByUserId(loggedInUser.getUser_ID());
            //menu.setJobTitle(currentUser.getPosition().getJob_Title());

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

        List<Company> companies = companyService.getCompanies();
        ChoiceBox<Company> companyChoiceBox = new ChoiceBox<>();
        companyChoiceBox.getItems().addAll(companies);
        companyChoiceBox.getSelectionModel().selectFirst();


        List<Position> positions = positionService.getPositions();
        ChoiceBox<Position> job_TitleCB = new ChoiceBox<>();
        job_TitleCB.getItems().addAll(positions);
        job_TitleCB.getSelectionModel().selectFirst();


        TextField areaCodeInput = new TextField();
        areaCodeInput.setPromptText("Area Code");

        TextField cityInput = new TextField();
        cityInput.setPromptText("City");

        TextField addressInput = new TextField();
        addressInput.setPromptText("Address");

        Button registerBtn = new Button("Registrer");
        registerBtn.setOnAction(e -> {
            if (emailInput.getText().isEmpty() || passwordInput.getText().isEmpty() || nameInput.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
            }
            else
            {
                User user = new User();
                user.setEmail(emailInput.getText());
                user.setPassword(passwordInput.getText());
                user.setName(nameInput.getText());
                user.setCompany_ID(job_TitleCB.getSelectionModel().getSelectedItem().getPosition_ID());
                user.setPosition_ID(companyChoiceBox.getSelectionModel().getSelectedItem().getCompany_ID());

                Address address = new Address();
                address.setPotalCode(areaCodeInput.getText());
                address.setCity(cityInput.getText());
                address.setAddress(addressInput.getText());

                userServiceinterface.register(user, address);
            }
        });

        Label statusLabel = new Label();

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(new Label("Email:"), emailInput,
                new Label("Adgangskode:"), passwordInput, new Label("Name:"), nameInput,
                new Label("Address:"), addressInput, new Label("Post kode:"), areaCodeInput,
                new Label("Company:"), companyChoiceBox, new Label("Job title:"), job_TitleCB,
                registerBtn, statusLabel);

        // Vis vinduet
        popupStage.setScene(new Scene(layout, 400, 500));
        popupStage.initModality(Modality.APPLICATION_MODAL); // Blokerer hovedvinduet
        popupStage.showAndWait();
    }



}
