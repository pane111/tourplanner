package Controller;

import ViewModel.SearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

public class SearchController {

    @FXML private TextField searchField;

    @Getter
    private SearchViewModel searchViewModel;

    public void initialize() {
        searchViewModel = new SearchViewModel();
        searchField.textProperty().bindBidirectional(searchViewModel.getSearchText());
    }

}
