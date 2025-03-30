package ViewModel;

import Controller.Mediator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class SearchViewModel {

    private final StringProperty searchText= new SimpleStringProperty("");

    public SearchViewModel() {
        Mediator.getInstance().srch=this;
    }

}
