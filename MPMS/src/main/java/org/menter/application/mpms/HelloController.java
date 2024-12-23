package org.menter.application.mpms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.menter.application.mpms.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.menter.application.mpms.service.DBConnection.getConnection;

public class HelloController {

    @FXML
    private TextField InputUsername;

    @FXML
    private PasswordField InputUserPassword;

    @FXML
    private Button loginButton;

    @FXML
    protected void login() {

        String email = InputUsername.getText();
        String password = InputUserPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty.", Alert.AlertType.ERROR);
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            if (connection == null) {
                throw new SQLException("Database connection failed.");
            }

            // Query to authenticate the user
            String query = "SELECT id, email, FirstName, LastName, Role FROM User WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password); // Use hashed passwords in real applications
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                //Create New User
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setRole(resultSet.getString("Role"));
                redirectToDashboard(user);

            } else {
                showAlert("Login Failed", "Invalid email or password.", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            showAlert("Error", "An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    private void redirectToDashboard(User user) {
        switch (user.getRole().toUpperCase()) {
            case "ADMIN":
                //showAlert("Login Successful", "Welcome, Admin! Redirecting to Admin Dashboard.", Alert.AlertType.INFORMATION);
                // Load Admin Dashboard
                loadAdminDashboard(user);
                InputUsername.setText("");
                InputUserPassword.setText("");
                break;
            case "MENTOR":
                //showAlert("Login Successful", "Welcome, Mentor! Redirecting to Mentor Dashboard.", Alert.AlertType.INFORMATION);
                // Load Mentor Dashboard
                loadMentorDashBoard(user);
                InputUsername.setText("");
                InputUserPassword.setText("");
                break;
            case "MENTEE":
                //showAlert("Login Successful", "Welcome, Mentee! Redirecting to Mentee Dashboard.", Alert.AlertType.INFORMATION);
                // Load Mentee Dashboard
                loadMenteeDashBoard(user);
                InputUsername.setText("");
                InputUserPassword.setText("");
                break;
            default:
                showAlert("Error", "Unrecognized role: " + user.getRole(), Alert.AlertType.ERROR);
                break;
        }
    }

    private void loadAdminDashboard(User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600); // Adjust dimensions as needed

            // Get the controller associated with the FXML file
            AdminDashboardController adminDashboardController = fxmlLoader.getController();

            // Set the admin profile in the controller
            adminDashboardController.setAdminProfile(user);
            adminDashboardController.initialize();


            // Create and display the stage
            Stage stage = new Stage();
            stage.setTitle("MPMS");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Close the current login window
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            showAlert("Error", "Failed to load Admin Dashboard: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadMentorDashBoard(User user) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MentorDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600); // Adjust dimensions as needed

            // Get the controller associated with the FXML file
            MentorDashboardController mentorDashboardController = fxmlLoader.getController();

            mentorDashboardController.setMentorProfile(user);
            mentorDashboardController.setMyGroups();


            // Create and display the stage
            Stage stage = new Stage();
            stage.setTitle("MPMS");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Close the current login window
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            showAlert("Error", "Failed to load Admin Dashboard: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadMenteeDashBoard(User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenteeDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600); // Adjust dimensions as needed

            // Get the controller associated with the FXML file
            MenteeDashboardController menteeDashboardController = fxmlLoader.getController();

            menteeDashboardController.setMenteeProfile(user);
            menteeDashboardController.setMyGroups();

            // Create and display the stage
            Stage stage = new Stage();
            stage.setTitle("MPMS");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            // Close the current login window
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            showAlert("Error", "Failed to load Admin Dashboard: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("MPMS");
        alert.setContentText(message);

        // Set the icon for the alert dialog
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));

        alert.showAndWait();
    }

}