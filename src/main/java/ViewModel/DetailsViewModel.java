package ViewModel;

import Model.Tour;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class DetailsViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to  = new SimpleStringProperty();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final StringProperty estimatedTime = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();

    public void setCurTour(Tour tour)
    {
        if (tour!=null)
        {
            name.set(tour.getName());
            from.set(tour.getFrom());
            to.set(tour.getTo());
            distance.set(tour.getDistance());
            estimatedTime.set(tour.getEstimatedTime());
            description.set(tour.getDescription());
            id.set(tour.getId());

        }
    }

}
