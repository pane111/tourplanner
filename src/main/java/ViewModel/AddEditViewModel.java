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
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    Logger LOGGER = LogManager.getLogger(AddEditViewModel.class);

    public TourDto createTour()
    {
        if (name.isEmpty().getValue() || from.isEmpty().getValue() || to.isEmpty().getValue()
        || description.isEmpty().getValue())
        {
            System.out.println("Empty Fields");
            return null;
        }
        LOGGER.info("Creating Tour with id: " + id.getValue());
        return new TourDto(name.getValue(),from.getValue(),to.getValue(),null,null,
                description.getValue(),null);

    }
    public TourDto createTourWithId()
    {
        if (name.isEmpty().getValue() || from.isEmpty().getValue() || to.isEmpty().getValue()
                || description.isEmpty().getValue())
        {
            System.out.println("Empty Fields");
            return null;
        }
        LOGGER.info("Creating Tour with id: " + id.getValue());
        return new TourDto(name.getValue(),from.getValue(),to.getValue(),null,null,
                description.getValue(),Long.valueOf(id.getValue()));

    }
}
