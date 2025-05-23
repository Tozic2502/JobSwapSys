package org.example.jobswapsystem;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.jobswapsystem.Models.User;

/**
 * Builds the UI layout for the Job Swap system, including the home screen,
 * navigation menu, user info panel, and search interface.
 */
public class MenuCreater {

    private ChoiceBox<String> jobTitleCB = new ChoiceBox<>();
    private ChoiceBox<String> distanceCB;
    private ChoiceBox<String> areaCodeCB;
    private ListView<String> resultsList;

    public BorderPane root = new BorderPane(); // Main layout container

    ListView<User> userListView = new ListView<>();

    Label homeLbl   = makeTab("Home");
    Label matchesLbl= makeTab("Matches");
    Label searchLbl = makeTab("Search");

    private Label nameLabel = new Label();
    private Label emailLabel = new Label();
    private Label jobLabel = new Label();
    private Label companyLabel = new Label();
    private Label locationLabel = new Label();

    /**
     * Creates the complete home screen layout including all UI panels.
     */
    public void createHomeScreen(){
        root.setPrefSize(1200, 800);
        topMenu();
        homemiddleMenu();
        homerightPanel();
        homeleftPanel();
    }

    /**
     * Creates the top navigation bar with tab labels.
     */
    public void topMenu(){
        HBox topMenu = new HBox(40);
        topMenu.setPadding(new Insets(15));
        topMenu.setStyle("-fx-background-color: #ececec;");
        topMenu.getChildren().addAll(homeLbl, matchesLbl, searchLbl);
        root.setTop(topMenu);
    }

    /**
     * Creates the center panel showing current matches and invitations.
     */
    private void homemiddleMenu(){
        VBox centerBox = new VBox(30);
        centerBox.setPadding(new Insets(20));
        centerBox.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");

        ListView<User> invitationListView = new ListView<>();
        ListView<User> currentMatchListView = new ListView<>();

        Label currentMatchesLbl = new Label("Current matches");
        currentMatchListView.setPrefHeight(200);

        Label invitationsLbl = new Label("Invitations");
        invitationListView.setPrefHeight(200);

        centerBox.getChildren().addAll(currentMatchesLbl, currentMatchListView, invitationsLbl, invitationListView);
        root.setCenter(centerBox);
    }

    /**
     * Creates the right panel displaying logged-in user information.
     */
    private void homerightPanel(){
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(20));
        rightBox.getChildren().addAll(
                new Label("Name: "), nameLabel,
                new Label("Email: "), emailLabel,
                new Label("Job position: "), jobLabel,
                new Label("Company: "), companyLabel,
                new Label("Location: "), locationLabel
        );
        rightBox.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");
        rightBox.setPrefWidth(250);
        root.setRight(rightBox);
    }

    /**
     * Creates the left panel for displaying matched users in contact.
     */
    private void homeleftPanel(){
        VBox leftWrapper = new VBox();
        leftWrapper.setPadding(new Insets(20));
        Label leftTitle = new Label("In contact matches");
        leftWrapper.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");
        leftWrapper.setPrefWidth(300);
        leftWrapper.getChildren().addAll(leftTitle, userListView);
        root.setLeft(leftWrapper);
    }

    /**
     * Utility method to create a styled tab label for navigation.
     *
     * @param text Tab name
     * @return Configured Label
     */
    private Label makeTab(String text) {
        Label lbl = new Label(text);
        lbl.setPadding(new Insets(5, 10, 5, 10));
        lbl.setCursor(Cursor.HAND);
        lbl.setStyle("-fx-border-color: transparent transparent #333 transparent;");
        lbl.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
                lbl.setStyle("-fx-border-color: transparent transparent #333 transparent; -fx-underline: true;"));
        lbl.addEventHandler(MouseEvent.MOUSE_EXITED, e ->
                lbl.setStyle("-fx-border-color: transparent transparent #333 transparent;"));
        return lbl;
    }

    /**
     * Builds the search UI with filters like job title, distance, and area code.
     */
    public void searchScreen(){

        ChoiceBox<String> distanceCB = new ChoiceBox<>();
        distanceCB.getItems().addAll("5 km", "10 km", "20 km");

        ChoiceBox<String> areaCodeCB = new ChoiceBox<>();
        areaCodeCB.getItems().addAll("1000", "2000", "3000");

        Button confirmBtn = new Button("Confirm");

        ListView<String> resultsList = new ListView<>();

        VBox form = new VBox(15, jobTitleCB, distanceCB, areaCodeCB, confirmBtn);
        form.setPadding(new Insets(20));
        form.setPrefWidth(300);
        form.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");

        // Right side: the results panel
        Label resultsLabel = new Label("Results from search");
        VBox resultsBox = new VBox(10, resultsLabel, resultsList);
        resultsBox.setPadding(new Insets(20));
        resultsBox.setStyle("-fx-border-color: #aaa; -fx-border-width: 1;");

        // Combine into one HBox and place in CENTER
        HBox searchContainer = new HBox(30, form, resultsBox);
        searchContainer.setPadding(new Insets(20));
        root.setCenter(searchContainer);
    }

    /**
     * Populates the right panel with the logged-in user's information.
     * @param user The current logged-in user
     */
    public void setCurrentUser(User user) {
        nameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());
        jobLabel.setText(user.getPosition().getJob_Title());
        companyLabel.setText(user.getCompany().getName());
        locationLabel.setText(user.getAddress().getCity());
    }

    /**
     * Sets the job title in the job title choice box.
     *
     * @param user The current logged-in user
     */
    public void setJobTitle(User user) {
        if (user != null && user.getPosition() != null) {
            String jobTitle = user.getPosition().getJob_Title();
            jobTitleCB.getItems().clear();
            jobTitleCB.getItems().addAll(jobTitle);
            jobTitleCB.setValue(jobTitle);
        }

    }
}
