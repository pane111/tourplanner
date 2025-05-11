package ViewModel;

import Model.TourDto;
import javafx.beans.property.*;
import lombok.Getter;

@Getter
public class DetailsViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to  = new SimpleStringProperty();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final StringProperty estimatedTime = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final LongProperty id = new SimpleLongProperty();

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
