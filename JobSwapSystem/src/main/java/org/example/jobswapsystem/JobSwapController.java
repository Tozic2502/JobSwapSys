package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.example.jobswapsystem.Models.*;
import org.example.jobswapsystem.Repository.UserRepository;
import org.example.jobswapsystem.Service.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import org.example.jobswapsystem.Service.SessionManager;

import java.util.List;

public class JobSwapController {
    MatchService matchService = new MatchService();
    IPositionService positionService = new PositionService();
    ICompanyService companyService = new CompanyService();
    User currentUser = new User();
    MenuCreater menu = new MenuCreater();
    UserService userService = new UserService(new UserRepository());
    ISessionManager sessionManager = new SessionManager();
    ILoginService loginService = new LoginService(userService, sessionManager);
    BorderPane root = menu.root;

    @FXML TextField emailTextField, passwordTextField;



    @FXML GridPane startScreen;

    /**
     * Called after FXML is loaded. Sets up button/menu handlers.
     */
    //Mikkel
    @FXML
    public void initialize() {
        handleHomeReturn();
        handleSearch();
        handleConfirmSwap();
        handleSendInvitation();
    }

    /**
     * Defines behavior when the Search label is clicked.
     * Clears the screen and displays the search view.
     */
    //Mikkel
    public void handleSearch(){
        menu.searchLbl.setOnMouseClicked(e -> root.getChildren().clear());
        menu.searchLbl.setOnMouseClicked(e -> menu.topMenu());
        menu.searchLbl.setOnMouseClicked(e -> menu.searchScreen());
    }

    /**
     * Placeholder for Confirm Swap label click behavior.
     */
    public void handleConfirmSwap(){ }

    /**
     * Placeholder for Send Invitation label click behavior.
     */
    public void handleSendInvitation(){ }

    /**
     * Defines behavior when the Home label is clicked.
     * Clears the screen and returns to the home view.
     */
    //Mikkel
    public void handleHomeReturn(){
        menu.homeLbl.setOnMouseClicked(e -> root.getChildren().clear());
        menu.homeLbl.setOnMouseClicked(e -> menu.createHomeScreen());
    }

    /**
     * Called when the login button is pressed.
     * Authenticates the user, loads their home screen, and sets up UI with user info.
     */
    //Mikkel
    @FXML
    public void onActionloginButton() {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        boolean success = loginService.login(email, password);

        if (success) {
            User currentUser = sessionManager.getCurrentUser();
            userService.getUserdDetails(currentUser);
            menu.setCurrentUser(currentUser);
            startScreen.getChildren().clear();
            startScreen.getChildren().add(menu.root);
            menu.setJobTitle(currentUser);
            menu.createHomeScreen();
        } else {
            System.out.println("Wrong credentials");
        }
    }

    /**
     * Opens a modal dialog for user registration.
     * Collects user info including email, password, job title, etc.
     */
    @FXML
    public void registerButton() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Registrer ny bruger");

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

                userService.register(user, address);
            }
        });

        Label statusLabel = new Label();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(new Label("Email:"), emailInput,
                new Label("Adgangskode:"), passwordInput, new Label("Name:"), nameInput,
                new Label("Address:"), addressInput, new Label("Post kode:"), areaCodeInput,
                new Label("Company:"), companyChoiceBox, new Label("Job title:"), job_TitleCB,
                registerBtn, statusLabel);

        popupStage.setScene(new Scene(layout, 400, 500));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }
}
