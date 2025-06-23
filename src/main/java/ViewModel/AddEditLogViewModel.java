package ViewModel;

import Controller.Mediator;
import Model.TourLogDto;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

@Getter
public class AddEditLogViewModel {

    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final StringProperty difficulty  = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    //Regex for date entry
    private static final String DATE_PATTERN = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
    private static final Pattern pattern = Pattern.compile(DATE_PATTERN);
    Logger logger = LogManager.getLogger(AddEditLogViewModel.class);

    public TourLogDto createLog()
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
                TourLogDto newLog = new TourLogDto(date.get(), comment.get(),
                        difficulty.get(),distance.get(),time.get(),r, Mediator.getInstance().selectedTourId.get());
                if (id.getValue()!=null)
                {
                    newLog.setLogId(Long.valueOf(id.getValue()));
                }
                logger.info("Created new log for tour " + newLog.getTourId());
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
