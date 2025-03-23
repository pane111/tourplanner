package View;

import Model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class TPController {
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

}