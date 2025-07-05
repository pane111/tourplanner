package Controller;

import Model.TourDto;
import ViewModel.ListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.attribute.standard.Media;


public class ListController {
    @FXML
    public ListView<TourDto> tourListView;
    @Getter
    private ListViewModel viewModel;

    Logger logger = LogManager.getLogger(ListController.class);

    public void initialize() {
        viewModel=new ListViewModel(Mediator.getInstance().srch.getSearchText());
        Mediator.getInstance().list = viewModel;
        Mediator.getInstance().listController = this;
        tourListView.setItems(viewModel.getFilteredTourDtos());

        tourListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                logger.info("Selected " +newValue.getName());
                Mediator.getInstance().details.enableMapButton();
            }
            else
            {
                logger.info("Selected null");
            }
        });

    }
    public TourDto getLastSelectedItem() {
        TourDto selectedTourDto = tourListView.getSelectionModel().getSelectedItems().getLast();
        return selectedTourDto;
    }



    public void onRefresh(ActionEvent actionEvent) {
        viewModel.refresh();
    }
}
