package org.menter.application.mpms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class CreateGroup extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-new-group.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 380, 520); // Adjust dimensions as needed
        stage.setTitle("MPMS");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}