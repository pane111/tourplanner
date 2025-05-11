package ViewModel;

import Model.TourDto;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.Setter;

public class ListViewModel {

    private final ObservableList<TourDto> tourDtos = FXCollections.observableArrayList();
    @Getter
    private final FilteredList<TourDto> filteredTourDtos;
    @Getter @Setter
    private TourDto selectedTourDto;


    TourDto dummyTourDto1 = new TourDto("Technikum Route", "Taborstraße, Vienna", "FH Technikum Wien", 1.0, "20 Minutes","Take the tram 2 from Taborstraße to FH Technikum Wien. Try not to be late!","1");
    TourDto dummyTourDto2 = new TourDto("Across Austria", "Vienna", "Innsbruck", 150.0, "6 hours", "Travel from Vienna through all of Austria!","2");
    TourDto dummyTourDto3 = new TourDto("Get Out Of Berlin", "Berlin", "Vienna", 300.0, "8 hours", "Leave Berlin Quickly", "3");

    public ListViewModel(StringProperty searchText) {
        tourDtos.add(dummyTourDto1);
        tourDtos.add(dummyTourDto2);
        tourDtos.add(dummyTourDto3);
        filteredTourDtos = new FilteredList<>(tourDtos, tour -> true);

        searchText.addListener((observable, oldValue, newValue) -> {
            filteredTourDtos.setPredicate(tour ->
                    newValue==null || newValue.isEmpty() || tour.getName().toLowerCase().contains(newValue.toLowerCase()));
        });

    }
    public void addTour(TourDto tourDto) {
        tourDto.setId(String.valueOf(tourDtos.size()+1));
        tourDtos.add(tourDto);
        for (TourDto t : tourDtos) {
            System.out.println(t.toString()+ ", ID: " +t.getId());
        }
    }
    public void removeTour(TourDto tourDto) {
        tourDtos.remove(tourDto);
    }



}
