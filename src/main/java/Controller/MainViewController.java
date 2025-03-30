package Controller;

import Model.Tour;
import ViewModel.DetailsViewModel;
import ViewModel.ListViewModel;
import ViewModel.SearchViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.List;

public class MainViewController {


    public void initialize() {
        ListController listController = Mediator.getInstance().listController;
        DetailsController detailsController = Mediator.getInstance().details;

        listController.tourListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detailsController.setTour(newValue);
        });

    }

    public void onAdd(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
            stage.setTitle("Add New Tour");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/modalStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onDelete(ActionEvent actionEvent) {
        Mediator.getInstance().list.removeTour(Mediator.getInstance().listController.getLastSelectedItem());
    }

    public void onEdit(ActionEvent actionEvent) {
        try {
            Tour selectedTour = Mediator.getInstance().listController.getLastSelectedItem();
            if (selectedTour != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edit-view.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
                stage.setTitle("Edit Tour");
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/modalStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                Mediator.getInstance().edit.fillFields(selectedTour);
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
