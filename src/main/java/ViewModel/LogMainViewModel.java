package ViewModel;

import Model.TourLogDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class LogMainViewModel {
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty comment = new SimpleStringProperty();
    private final StringProperty difficulty  = new SimpleStringProperty();
    private final StringProperty distance = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();

    public void setCurLog(TourLogDto log)
    {
        System.out.println(log.getComment());
        if (log!=null)
        {
            date.set(log.getDate());
            comment.set(log.getComment());
            difficulty.set(log.getDifficulty());
            distance.set(log.getDistance());
            time.set(log.getTime());
            rating.set(log.getRatingString());
        }
    }

}
