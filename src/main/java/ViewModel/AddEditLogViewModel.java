package ViewModel;

import Controller.Mediator;
import Model.TourLog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class AddEditLogViewModel {

    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final StringProperty difficulty  = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    //Regex for date entry
    private static final String DATE_PATTERN = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$";
    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);
    public TourLog createLog()
    {
        if (date.isEmpty().getValue() || comment.isEmpty().getValue() || difficulty.isEmpty().getValue() || distance.isEmpty().getValue()
            || time.isEmpty().getValue() || rating.isEmpty().getValue())
        {
            System.out.println("Empty fields");
            return null;
        }
        else if (!pattern.matcher(date.get()).matches())
        {
            System.out.println("Date wrong");
            return null;
        }
        else
        {
            try {
                Integer r = Integer.valueOf(rating.get());
                if (r < 0)
                    r=0;
                if (r>5)
                    r=5;
                TourLog newLog = new TourLog(date.get(), comment.get(),
                        difficulty.get(),distance.get(),time.get(),r, Mediator.getInstance().selectedTourId.get());
                System.out.println(newLog);
                return newLog;

            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
