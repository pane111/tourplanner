package Controller;

import Model.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML private Button logButton;
    @FXML private Button detailButton;


    private Integer curView=0; //0 = Details, 1 = Logs

    public void initialize() {
        ListController listController = Mediator.getInstance().listController;
        DetailsController detailsController = Mediator.getInstance().details;

        listController.tourListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detailsController.setTour(newValue);
            if (newValue != null) {
                Mediator.getInstance().selectedTourId.setValue(Integer.parseInt(newValue.getId()));
            }

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

    public void displayDetails(ActionEvent actionEvent) {
        if (curView!=0)
        {
            curView=0;
            Mediator.getInstance().details.showDetails();
        }
    }

    public void displayLogs(ActionEvent actionEvent) {
        if (curView!=1 && Mediator.getInstance().listController.getLastSelectedItem()!=null)
        {
            curView=1;
            Mediator.getInstance().details.showLogs();
        }
    }
}
