package ViewModel;

import Model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class AddEditViewModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to  = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty estimatedTime = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();

    public Tour createTour()
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
        return new Tour(name.getValue(),from.getValue(),to.getValue(),Double.parseDouble(distance.getValue()),estimatedTime.getValue(),
                description.getValue(),id.getValue());

    }
}
