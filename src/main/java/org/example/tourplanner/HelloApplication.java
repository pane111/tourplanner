package org.example.tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Load the CSS file
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(450);
        primaryStage.setTitle("Tour Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}