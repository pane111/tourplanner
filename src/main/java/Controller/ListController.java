package Controller;

import Model.Tour;
import ViewModel.ListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.Getter;
import lombok.Setter;

import javax.print.attribute.standard.Media;

public class ListController {
    @FXML
    public ListView<Tour> tourListView;
    @Getter
    private ListViewModel viewModel;

    public void initialize() {
        viewModel=new ListViewModel(Mediator.getInstance().srch.getSearchText());
        Mediator.getInstance().list = viewModel;
        Mediator.getInstance().listController = this;
        tourListView.setItems(viewModel.getFilteredTours());


    }
    public Tour getLastSelectedItem() {
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItems().getLast();
        return selectedTour;
    }


}
