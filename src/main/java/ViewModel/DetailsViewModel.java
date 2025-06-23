package ViewModel;

import Model.TourDto;
import javafx.beans.property.*;
import lombok.Getter;

@Getter
public class DetailsViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to  = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
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
            distance.set(String.format("%.2f", tourDto.getDistance()) + " km");

            String rawTime = tourDto.getEstimatedTime();
            Double parsedTime = Double.valueOf(rawTime);
            if (parsedTime != null)
            {
                int seconds = parsedTime.intValue();
                int hours = seconds / 3600;
                int minutes = (seconds% 3600) / 60;
                estimatedTime.set(hours + " hours, " + minutes + " minutes");
            }
            else
            {
                estimatedTime.set(tourDto.getEstimatedTime());
            }


            description.set(tourDto.getDescription());
            id.set(tourDto.getId());

        }
    }

}
