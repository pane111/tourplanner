package ViewModel;

import Controller.Mediator;
import Model.TourDto;
import Model.TourLogDto;
import bl.LogService;
import bl.TourService;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class LogListViewModel {

    private final ObservableList<TourLogDto> logs = FXCollections.observableArrayList();
    @Getter
    private final FilteredList<TourLogDto> filteredLogs;
    @Getter @Setter
    private TourLogDto selectedLog;
    Logger LOGGER = LogManager.getLogger(ListViewModel.class);
    private final LogService logService=new LogService();



    public LogListViewModel(LongProperty id) {
        Mediator.getInstance().logService = logService;
        TourLogDto[] retrievedTours = logService.fetchTours();
        logs.addAll(retrievedTours);
        LOGGER.info("Retrieved " + logs.size() + " tour logs");
        for (TourLogDto tourLog : logs) {
            LOGGER.info("Log: "+tourLog.getLogId());
        }

        filteredLogs = new FilteredList<>(logs, log -> true);
        id.addListener((obs, oldVal, newVal) -> {
            filteredLogs.setPredicate(log ->
                    Objects.equals(log.getTourId(), newVal));
        });
    }
    public void refresh()
    {
        logs.clear();
        TourLogDto[] retrievedTours = logService.fetchTours();
        logs.addAll(retrievedTours);
    }

    public void add(TourLogDto log) {
        logService.createLog(log);
        refresh();
    }
    public void remove(TourLogDto log) {
        logService.deleteLog(log);
        refresh();
    }
    public void update(TourLogDto log) {
        logService.updateLog(log);
        refresh();
    }


}
