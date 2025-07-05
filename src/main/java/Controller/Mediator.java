package Controller;

import Model.TourDto;
import ViewModel.AddEditViewModel;
import ViewModel.ListViewModel;
import ViewModel.SearchViewModel;
import bl.LogService;
import bl.TourService;
import javafx.application.HostServices;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import lombok.Getter;
import lombok.Setter;

public class Mediator {

    public EditController edit;
    @Setter @Getter
    private TourDto tourDto;

    public LongProperty selectedTourId=new SimpleLongProperty();

    public SearchViewModel srch;
    public ListViewModel list;
    public ListController listController;
    public DetailsController details;
    public LogMainController log;
    public LogListController logList;
    public EditLogController editLog;
    public AddEditViewModel addEdit;
    public LogService logService;
    public HostServices hostServices;


    public TourService tourService;

    private Mediator() {
        selectedTourId.set(0);
    }
    public static Mediator getInstance() {
        return MediatorHolder.INSTANCE;
    }

    private static class MediatorHolder {
        private static final Mediator INSTANCE = new Mediator();

    }




}
