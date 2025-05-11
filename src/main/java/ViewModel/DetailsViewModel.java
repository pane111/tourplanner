package ViewModel;

import Model.TourDto;
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

    public void setCurTour(TourDto tourDto)
    {
        if (tourDto !=null)
        {
            name.set(tourDto.getName());
            from.set(tourDto.getFrom());
            to.set(tourDto.getTo());
            distance.set(tourDto.getDistance());
            estimatedTime.set(tourDto.getEstimatedTime());
            description.set(tourDto.getDescription());
            id.set(tourDto.getId());

        }
    }

}
