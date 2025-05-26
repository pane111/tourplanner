package ViewModel;

import Model.TourLogDto;
import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class LogListViewModel {

    private final ObservableList<TourLogDto> logs = FXCollections.observableArrayList();
    @Getter
    private final FilteredList<TourLogDto> filteredLogs;
    @Getter @Setter
    private TourLogDto selectedLog;

    TourLogDto dummyLog1 = new TourLogDto("01/01/2001","This tour was awesome!",
            "Perfect","100","20 Minutes",5,1L);
    TourLogDto dummyLog2 = new TourLogDto("12/12/2024","This tour was awful!",
            "Way too difficult","100","5 Hours",0,1L);
    TourLogDto dummyLog3 = new TourLogDto("06/02/2023","I enjoyed this tour. Am happy to be out of Berlin!",
            "Challenging","1000","2 Hours",3,3L);

    public LogListViewModel(LongProperty id) {
        logs.add(dummyLog1);
        logs.add(dummyLog2);
        logs.add(dummyLog3);

        filteredLogs = new FilteredList<>(logs, log -> true);
        id.addListener((obs, oldVal, newVal) -> {
            filteredLogs.setPredicate(log ->
                    Objects.equals(log.getTourId(), newVal));
        });
    }
    public void add(TourLogDto log) {
        logs.add(log);
    }
    public void remove(TourLogDto log) {
        logs.remove(log);
    }


}
