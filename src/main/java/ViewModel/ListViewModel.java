package ViewModel;

import Model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.Setter;

public class ListViewModel {

    private final ObservableList<Tour> tours = FXCollections.observableArrayList();
    @Getter
    private final FilteredList<Tour> filteredTours;
    @Getter @Setter
    private Tour selectedTour;


    Tour dummyTour1 = new Tour("Technikum Route", "Taborstraße, Vienna", "FH Technikum Wien", 1.0, "20 Minutes","Take the tram 2 from Taborstraße to FH Technikum Wien. Try not to be late!","0");
    Tour dummyTour2 = new Tour("Across Austria", "Vienna", "Innsbruck", 150.0, "6 hours", "Travel from Vienna through all of Austria!","1");
    Tour dummyTour3 = new Tour("Get Out Of Berlin", "Berlin", "Vienna", 300.0, "8 hours", "Leave Berlin Quickly", "2");

    public ListViewModel(StringProperty searchText) {
        tours.add(dummyTour1);
        tours.add(dummyTour2);
        tours.add(dummyTour3);
        filteredTours = new FilteredList<>(tours, tour -> true);

        searchText.addListener((observable, oldValue, newValue) -> {
            filteredTours.setPredicate(tour ->
                    newValue==null || newValue.isEmpty() || tour.getName().toLowerCase().contains(newValue.toLowerCase()));
        });

    }
    public void addTour(Tour tour) {
        tours.add(tour);
    }
    public void removeTour(Tour tour) {
        tours.remove(tour);
    }



}
