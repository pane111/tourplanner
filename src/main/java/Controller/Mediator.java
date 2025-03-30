package Controller;

import Model.Tour;
import ViewModel.ListViewModel;
import ViewModel.SearchViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;

public class Mediator {

    public EditController edit;
    @Setter @Getter
    private Tour tour;

    public IntegerProperty selectedTourId=new SimpleIntegerProperty();

    public SearchViewModel srch;
    public ListViewModel list;
    public ListController listController;
    public DetailsController details;
    public LogMainController log;

    private Mediator() {
        selectedTourId.set(0);
    }
    public static Mediator getInstance() {
        return MediatorHolder.INSTANCE;
    }

    private static class MediatorHolder {
        private static final Mediator INSTANCE = new Mediator();

    }

    public void addToList(Tour ntour) {
        list.addTour(ntour);
    }




}
