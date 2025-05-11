package Controller;

import Model.TourDto;
import ViewModel.ListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lombok.Getter;

public class ListController {
    @FXML
    public ListView<TourDto> tourListView;
    @Getter
    private ListViewModel viewModel;

    public void initialize() {
        viewModel=new ListViewModel(Mediator.getInstance().srch.getSearchText());
        Mediator.getInstance().list = viewModel;
        Mediator.getInstance().listController = this;
        tourListView.setItems(viewModel.getFilteredTourDtos());


    }
    public TourDto getLastSelectedItem() {
        TourDto selectedTourDto = tourListView.getSelectionModel().getSelectedItems().getLast();
        return selectedTourDto;
    }


    public void onRefresh(ActionEvent actionEvent) {
        viewModel.refresh();
    }
}
