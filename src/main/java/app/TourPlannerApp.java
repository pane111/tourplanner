package app;

import Controller.DetailsController;
import Controller.Mediator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TourPlannerApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);


        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());



        primaryStage.setMinWidth(1400);
        primaryStage.setMinHeight(650);

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        primaryStage.setTitle("Tour Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
        Mediator.getInstance().details.setHostServices(this.getHostServices());
    }

    public static void main(String[] args) {
        launch(args);
    }
}