package org.menter.application.mpms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static org.menter.application.mpms.service.DBConnection.closeConnection;
import static org.menter.application.mpms.service.DBConnection.getConnection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("MPMS");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("appicon.png"))));

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {

        HelloApplication.saveInitialUsers();
        launch();


    }
    public static void saveInitialUsers() {
        Connection connection = null;
        try {
            connection = getConnection();
            if (connection == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            // Check if the User table is empty
            String checkQuery = "SELECT COUNT(*) AS count FROM User";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt("count") == 0) {
                System.out.println("User table is empty. Inserting initial data...");

                // Insert one admin
                String insertAdmin = "INSERT INTO User (email, password, FirstName, LastName, Role) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertAdminStmt = connection.prepareStatement(insertAdmin);
                insertAdminStmt.setString(1, "admin@example.com");
                insertAdminStmt.setString(2, "admin123"); // Hash this in real applications
                insertAdminStmt.setString(3, "John");
                insertAdminStmt.setString(4, "Doe");
                insertAdminStmt.setString(5, "ADMIN");
                insertAdminStmt.executeUpdate();

                // Insert five mentors
                String[][] mentors = {
                        {"mentor1@example.com", "Alice", "Smith"},
                        {"mentor2@example.com", "Bob", "Johnson"},
                        {"mentor3@example.com", "Carol", "Williams"},
                        {"mentor4@example.com", "David", "Brown"},
                        {"mentor5@example.com", "Eve", "Davis"}
                };

                for (String[] mentor : mentors) {
                    PreparedStatement insertMentorStmt = connection.prepareStatement(insertAdmin);
                    insertMentorStmt.setString(1, mentor[0]);
                    insertMentorStmt.setString(2, "mentor123"); // Hash this in real applications
                    insertMentorStmt.setString(3, mentor[1]);
                    insertMentorStmt.setString(4, mentor[2]);
                    insertMentorStmt.setString(5, "MENTOR");
                    insertMentorStmt.executeUpdate();
                }

                // Insert five mentees
                String[][] mentees = {
                        {"mentee1@example.com", "Grace", "Miller"},
                        {"mentee2@example.com", "Henry", "Taylor"},
                        {"mentee3@example.com", "Isabel", "Anderson"},
                        {"mentee4@example.com", "Jack", "Thomas"},
                        {"mentee5@example.com", "Karen", "Moore"}
                };

                for (String[] mentee : mentees) {
                    PreparedStatement insertMenteeStmt = connection.prepareStatement(insertAdmin);
                    insertMenteeStmt.setString(1, mentee[0]);
                    insertMenteeStmt.setString(2, "mentee123"); // Hash this in real applications
                    insertMenteeStmt.setString(3, mentee[1]);
                    insertMenteeStmt.setString(4, mentee[2]);
                    insertMenteeStmt.setString(5, "MENTEE");
                    insertMenteeStmt.executeUpdate();
                }

                System.out.println("Initial data insertion completed.");
            } else {
                System.out.println("User table already contains data. Skipping initial data insertion.");
            }
        } catch (SQLException e) {
            System.err.println("Error while inserting initial data: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }

    }
}