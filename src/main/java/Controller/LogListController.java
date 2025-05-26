package Controller;

import Model.TourLogDto;
import ViewModel.LogListViewModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.Getter;

public class LogListController {

    public ListView<TourLogDto> logListView;
    @Getter
    public LogListViewModel viewModel;


    public void initialize() {
        Mediator.getInstance().selectedTourId.set(0);
        Mediator.getInstance().logList=this;
        viewModel = new LogListViewModel(Mediator.getInstance().selectedTourId);
        logListView.setItems(viewModel.getFilteredLogs());


        logListView.setCellFactory(param -> new ListCell<TourLogDto>() {
            @Override
            protected void updateItem(TourLogDto item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDate());
                }
            }
        });

        logListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                System.out.println(newValue.getDate());
                Mediator.getInstance().log.setLog(newValue);
            }
        });


    }
    public void add(TourLogDto log) {
        viewModel.add(log);
    }

    public void removeLog(TourLogDto log) {
        viewModel.remove(log);
    }

    public void updateLog(TourLogDto log) {
        viewModel.update(log);
    }

    public TourLogDto getLastSelectedItem() {
        TourLogDto selectedLog = logListView.getSelectionModel().getSelectedItems().getLast();
        return selectedLog;

    }
}


