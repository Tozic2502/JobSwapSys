package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Service.MatchService;

public class JobSwapController {
    MatchService matchService = new MatchService();
    User currentUser = new User();
     MenuCreater menu = new MenuCreater();
    BorderPane root = menu.root;



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
    public void onActionloginButton(){
        startScreen.getChildren().clear();
        startScreen.getChildren().add(root);
        menu.createHomeScreen();
    }
    @FXML
    public void registerButton(){

    }

}
