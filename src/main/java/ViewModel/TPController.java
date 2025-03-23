package ViewModel;

import Model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TPController extends Controller {
    @FXML
    private Text tourName;
    @FXML
    private Text tourFrom;
    @FXML
    private Text tourTo;
    @FXML
    private Text tourDistance;
    @FXML
    private Text tourTime;
    @FXML
    private Text tourDesc;


    @FXML
    private ListView<Tour> tourListView;

    private ObservableList<Tour> tours = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        Mediator.getInstance().tpc=this;
        tours.add(new Tour("Technikum Route", "Taborstraße, Vienna", "FH Technikum Wien", 1.0, "20 Minutes","Take the tram 2 from Taborstraße to FH Technikum Wien. Try not to be late!","0"));
        tours.add(new Tour("Across Austria", "Vienna", "Innsbruck", 150.0, "6 hours", "Travel from Vienna through all of Austria!","1"));
        tours.add(new Tour("Get Out Of Berlin", "Berlin", "Vienna", 300.0, "8 hours", "Leave Berlin Quickly", "2"));

        tourListView.setItems(tours);

        tourListView.setCellFactory(listView -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour tour, boolean empty) {
                super.updateItem(tour, empty);
                if (empty || tour == null) {
                    setText(null);
                } else {
                    setText(tour.getName()); // Display tour name in the list
                }
            }
        });


    }

    public void addTourToList(Tour tour) {
        tours.add(tour);
        tourListView.setItems(tours);
    }

    public void removeLastSelectedTour() {
        tours.remove(tourListView.getSelectionModel().getSelectedItem());
        tourListView.setItems(tours);
    }

    @FXML
    private void onTourSelected() {
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            tourName.setText(selectedTour.getName());
            tourFrom.setText(selectedTour.getFrom());
            tourTo.setText(selectedTour.getTo());
            tourDistance.setText(selectedTour.getDistance().toString());
            tourDesc.setText(selectedTour.getDescription());

            tourTime.setText(selectedTour.getEstimatedTime());
        }
    }

    @FXML
    private void onEditClick() {
        try {
            Tour selectedTour = tourListView.getSelectionModel().getSelectedItems().getLast();
            if (selectedTour != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edit-view.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();

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



    @FXML
    private void onAddClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();

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

    @FXML
    private void onDeleteClick() {
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItems().getLast();
        if (selectedTour != null) {
            tours.remove(selectedTour);
            tourListView.setItems(tours);
            tourName.setText("");
            tourFrom.setText("");
            tourTo.setText("");
            tourDistance.setText("");
            tourDesc.setText("");
            tourTime.setText("");
            tourListView.getSelectionModel().clearSelection();
        }
    }

}