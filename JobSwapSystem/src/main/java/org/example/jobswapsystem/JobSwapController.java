package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Service.MatchService;

public class JobSwapController {
    MatchService matchService = new MatchService();
    User currentUser = new User();
    ListView<User> userListView = new ListView<>();
    ListView<User> invitationListView = new ListView<>();
    ListView<User> currentMatchListView = new ListView<>();
    BorderPane root = new BorderPane();


    @FXML
    public void initialize() {

    }
    public void handleSearch(){

    }
    public void handleConfirmSwap(){

    }
    public void handleSendInvitation(){

    }
    @FXML
    public void loginButton(){

    }
    @FXML
    public void registerButton(){

    }
    public void createHomeScreen(){
        root.setPrefSize(1200, 800);
        topMenu();
        middleMenu();
        rightPanel();
        leftPanel();



    }
    private void topMenu(){
        HBox topMenu = new HBox(40);
        topMenu.setPadding(new Insets(15));
        topMenu.setStyle("-fx-background-color: #ececec;");
        topMenu.getChildren().addAll(
                new Label("Home"),
                new Label("Matches"),
                new Label("Search")
        );
        root.setTop(topMenu);
    }
    private void middleMenu(){
        VBox centerBox = new VBox(30);
        centerBox.setPadding(new Insets(20));
        centerBox.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");
        ListView<User> invitationListView = new ListView<>();
        ListView<User> currentMatchListView = new ListView<>();

        Label currentMatchesLbl = new Label("Current matches");
        currentMatchListView.setPrefHeight(200);

        Label invitationsLbl = new Label("Invitations");
        invitationListView.setPrefHeight(200);

        centerBox.getChildren().addAll(
                currentMatchesLbl, currentMatchListView,
                invitationsLbl, invitationListView
        );
        root.setCenter(centerBox);
    }
    private void rightPanel(){
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(20));
        rightBox.getChildren().addAll(
                new Label("Name: "),
                new Label("Email: "),
                new Label("Job position: "),
                new Label("Company: "),
                new Label("Location: ")
        );
        rightBox.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");
        rightBox.setPrefWidth(250);
        root.setRight(rightBox);

    }
    private void leftPanel(){
        VBox leftWrapper = new VBox();
        leftWrapper.setPadding(new Insets(20));
        Label leftTitle = new Label("In contact matches");
        leftWrapper.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");
        leftWrapper.setPrefWidth(300);
        leftWrapper.getChildren().addAll(leftTitle, userListView);
        root.setRight(leftWrapper);
    }
}
