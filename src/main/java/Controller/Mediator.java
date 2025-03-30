package Controller;

import Model.Tour;
import ViewModel.ListViewModel;
import ViewModel.SearchViewModel;
import lombok.Getter;
import lombok.Setter;

public class Mediator {

    public EditController edit;
    @Setter @Getter
    private Tour tour;

    public SearchViewModel srch;
    public ListViewModel list;
    public ListController listController;
    public DetailsController details;

    private Mediator() {}
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
