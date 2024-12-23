package org.menter.application.mpms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static org.menter.application.mpms.service.DBConnection.closeConnection;
import static org.menter.application.mpms.service.DBConnection.getConnection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("MPMS");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("appicon.png"))));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

//        Connection connection = null;
//        try {
//            connection = getConnection(); // Try to get a connection
//            if (connection != null) {
//                System.out.println("Connection test successful!");
//            }
//        } catch (SQLException e) {
//            System.err.println("Error while testing the connection: " + e.getMessage());
//        } finally {
//            closeConnection(connection); // Ensure the connection is closed
//        }
        launch();


    }
}