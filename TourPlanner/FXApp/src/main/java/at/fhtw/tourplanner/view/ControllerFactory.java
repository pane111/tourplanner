package at.fhtw.tourplanner.view;

import at.fhtw.tourplanner.viewmodel.MainWindowViewModel;
import at.fhtw.tourplanner.viewmodel.SearchBarViewModel;
import at.fhtw.tourplanner.viewmodel.MediaDetailsViewModel;
import at.fhtw.tourplanner.viewmodel.MediaOverviewViewModel;

public class ControllerFactory {
    private final MainWindowViewModel mainWindowViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final MediaOverviewViewModel mediaOverviewViewModel;
    private final MediaDetailsViewModel mediaDetailsViewModel;

    public ControllerFactory() {
        searchBarViewModel = new SearchBarViewModel();
        mediaOverviewViewModel = new MediaOverviewViewModel();
        mediaDetailsViewModel = new MediaDetailsViewModel();
        mainWindowViewModel = new MainWindowViewModel(searchBarViewModel, mediaOverviewViewModel, mediaDetailsViewModel);
    }

    //
    // Factory-Method Pattern
    //
    public Object create(Class<?> controllerClass) {
        if (controllerClass == MainWindowController.class) {
            return new MainWindowController(mainWindowViewModel);
        } else if (controllerClass == SearchBarController.class) {
            return new SearchBarController(searchBarViewModel);
        } else if (controllerClass == MediaDetailsController.class) {
            return new MediaDetailsController(mediaDetailsViewModel);
        } else if (controllerClass == MediaOverviewController.class) {
            return new MediaOverviewController(mediaOverviewViewModel);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass);
    }


    //
    // Singleton-Pattern with early-binding
    //
    private static final ControllerFactory instance = new ControllerFactory();

    public static ControllerFactory getInstance() {
        return instance;
    }

}
