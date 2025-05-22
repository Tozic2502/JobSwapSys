package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Service.MatchService;
import org.example.jobswapsystem.Service.UserService;
import org.example.jobswapsystem.util.SqlConnection;
//Mikkel
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
            userService.getJobTitleByUserId(loggedInUser.getUserID());
            menu.setJobTitle(currentUser.getJobTitle());
            System.out.println(currentUser.getJobTitle());



            menu.setCurrentUser(loggedInUser);
            menu.createHomeScreen();


        } else {
            System.out.println("Wrong credentials");
        }
    }

    @FXML
    public void registerButton(){

    }

}
