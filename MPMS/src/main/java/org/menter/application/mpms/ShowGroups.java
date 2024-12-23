package org.menter.application.mpms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ShowGroups extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("created-groups-show.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 289); // Adjust dimensions as needed
        stage.setTitle("MPMS");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("appicon.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
