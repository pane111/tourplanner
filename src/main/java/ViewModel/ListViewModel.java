package ViewModel;

import Controller.Mediator;
import Model.TourDto;
import bl.TourService;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListViewModel {

    private final ObservableList<TourDto> tourDtos = FXCollections.observableArrayList();
    @Getter
    private final FilteredList<TourDto> filteredTourDtos;
    @Getter @Setter
    private TourDto selectedTourDto;
    Logger LOGGER = LogManager.getLogger(ListViewModel.class);
    private final TourService tourService=new TourService();


    public ListViewModel(StringProperty searchText) {
        Mediator.getInstance().tourService=tourService;
        TourDto[] retrievedTours = tourService.fetchTours();
        tourDtos.addAll(retrievedTours);
        filteredTourDtos = new FilteredList<>(tourDtos, tour -> true);
        LOGGER.info("Current list of tours is " + tourDtos);
        searchText.addListener((observable, oldValue, newValue) -> {
            filteredTourDtos.setPredicate(tour ->
                    newValue==null || newValue.isEmpty() || tour.getName().toLowerCase().contains(newValue.toLowerCase()));
        });

    }
    public void refresh()
    {
        tourDtos.clear();
        TourDto[] retrievedTours = tourService.fetchTours();
        tourDtos.addAll(retrievedTours);
    }
    public void addTour(TourDto tourDto) {

        tourDtos.add(tourDto);
        for (TourDto t : tourDtos) {
            System.out.println(t.toString()+ ", ID: " +t.getId());
        }
    }
    public void removeTour(TourDto tourDto) {
        tourDtos.remove(tourDto);
    }



}
