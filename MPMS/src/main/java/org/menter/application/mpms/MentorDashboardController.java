package org.menter.application.mpms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.menter.application.mpms.dao.GroupDAO;
import org.menter.application.mpms.entity.MentorGroup;
import org.menter.application.mpms.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MentorDashboardController {

    @FXML
    private Label userID;
    @FXML
    private Label fullName;
    @FXML
    private Label email;
    @FXML
    private Label Role;

    @FXML
    private Button logOutButton;

    @FXML
    private TableView<MentorGroup> myGroupsTable;
    @FXML
    private TableColumn<MentorGroup, Integer> GroupID;
    @FXML
    private TableColumn<MentorGroup, String> GroupName;
    @FXML
    private TableColumn<MentorGroup, Integer> MenteeID;
    @FXML
    private TableColumn<MentorGroup, String> MenteeName;

    private final GroupDAO groupDAO= new GroupDAO();

    private User user;

    public void setMentorProfile(User user) {

        this.user = user;
        userID.setText(String.valueOf(user.getId()));
        fullName.setText(user.getFirstName()+" "+user.getLastName());
        email.setText(user.getEmail());
        Role.setText(user.getRole());

    }

    public void setMyGroups() {
        // Set up columns
        GroupID.setCellValueFactory(new PropertyValueFactory<>("id"));
        GroupName.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        MenteeID.setCellValueFactory(new PropertyValueFactory<>("MenteeID"));
        MenteeName.setCellValueFactory(new PropertyValueFactory<>("MenteeName"));

        // Fetch and display users
        getAllMyGroups();
    }

    public void getAllMyGroups() {

        List<MentorGroup> myGroups = groupDAO.getMyGroups(this.user.getId());

        // Combine the lists
        List<MentorGroup> myGroupsSet = new ArrayList<>(myGroups);

        // Populate the TableView
        ObservableList<MentorGroup> userList = FXCollections.observableArrayList(myGroupsSet);
        myGroupsTable.setItems(userList);

    }

    @FXML
    protected void logOut() {

        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("You will be redirected to the login page.");

        // Wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Load the login page
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Scene loginScene = new Scene(fxmlLoader.load());

                // Set up a new stage for the login page
                Stage loginStage = new Stage();
                loginStage.setTitle("MPMS");
                loginStage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("appicon.png"))));
                loginStage.setScene(loginScene);
                loginStage.setResizable(false);
                loginStage.show();

                // Close the current admin dashboard window
                Stage currentStage = (Stage) logOutButton.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Optionally, show an alert to the user if there's an error
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Logout Failed");
                errorAlert.setContentText("An error occurred while logging out. Please try again.");
                errorAlert.showAndWait();
            }
        }
    }
}
