package ViewModel;

import Model.TourDto;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class AddEditViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to  = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty estimatedTime = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    Logger LOGGER = LogManager.getLogger(AddEditViewModel.class);

    public TourDto createTour()
    {
        if (name.isEmpty().getValue() || from.isEmpty().getValue() || to.isEmpty().getValue()
        || estimatedTime.isEmpty().getValue() || description.isEmpty().getValue())
        {
            System.out.println("Empty Fields");
            return null;
        }
        try {
            Double.parseDouble(distance.getValue());

        }
        catch (NumberFormatException e) {
            //error
            System.out.println("Invalid Distance");
            return null;
        }
        LOGGER.info("Creating Tour with id: " + id.getValue());
        return new TourDto(name.getValue(),from.getValue(),to.getValue(),Double.parseDouble(distance.getValue()),estimatedTime.getValue(),
                description.getValue(),Long.parseLong(id.getValue()));

    }
}
