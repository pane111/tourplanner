package ViewModel;

import Model.Tour;
import lombok.Getter;
import lombok.Setter;

public class Mediator {

    @Setter @Getter
    private Tour tour;

    public TPController tpc;
    public EditController edit;

    private Mediator() {}
    public static Mediator getInstance() {
        return MediatorHolder.INSTANCE;
    }

    private static class MediatorHolder {
        private static final Mediator INSTANCE = new Mediator();
    }




}
