package Controller;

import Model.Tour;
import Model.TourLog;
import ViewModel.LogListViewModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.Getter;

import javax.print.attribute.standard.Media;

public class LogListController {

    public ListView<TourLog> logListView;
    @Getter
    public LogListViewModel viewModel;


    public void initialize() {
        Mediator.getInstance().selectedTourId.set(0);
        Mediator.getInstance().logList=this;
        viewModel = new LogListViewModel(Mediator.getInstance().selectedTourId);
        logListView.setItems(viewModel.getFilteredLogs());


        logListView.setCellFactory(param -> new ListCell<TourLog>() {
            @Override
            protected void updateItem(TourLog item, boolean empty) {
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
    public void add(TourLog log) {
        viewModel.add(log);
    }

    public void removeLog(TourLog log) {
        viewModel.remove(log);
    }

    public TourLog getLastSelectedItem() {
        TourLog selectedLog = logListView.getSelectionModel().getSelectedItems().getLast();
        return selectedLog;

    }
}


