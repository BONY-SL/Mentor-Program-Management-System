package org.menter.application.mpms;


import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.menter.application.mpms.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdminDashboardController {

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
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> roleColumn;

    private final UserDAO userDAO = new UserDAO();


    public void setAdminProfile(User user) {

        userID.setText(String.valueOf(user.getId()));
        fullName.setText(user.getFirstName()+" "+user.getLastName());
        email.setText(user.getEmail());
        Role.setText(user.getRole());

    }

    public void initialize() {
        // Set up columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Fetch and display users
        getAllMentorsAndMentees();
    }

    public void getAllMentorsAndMentees() {
        // Fetch mentors and mentees from the database
        List<User> mentors = userDAO.getUsersByRole("MENTOR");
        List<User> mentees = userDAO.getUsersByRole("MENTEE");

        // Combine the lists
        List<User> users = new ArrayList<>();
        users.addAll(mentors);
        users.addAll(mentees);

        // Populate the TableView
        ObservableList<User> userList = FXCollections.observableArrayList(users);
        userTable.setItems(userList);
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
        } else {
            // User chose CANCEL or closed the dialog, so does nothing
        }
    }

}
