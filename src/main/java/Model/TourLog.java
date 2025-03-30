package Model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TourLog {
    private String date;
    private String comment;
    private String difficulty;
    private String distance;
    private String time;
    private Integer rating;
    private Integer tourId;

    public TourLog(String date, String comment, String difficulty, String distance, String time, Integer rating, Integer tourId) {
        this.date = date;
        this.comment = comment;
        this.difficulty = difficulty;
        this.distance = distance;
        this.time = time;
        this.rating = rating;
        this.tourId = tourId;

    }

    public void setRating(Integer rating) {
        this.rating = rating;
        if (this.rating<0)
            this.rating = 0;
        if (this.rating>5)
            this.rating = 5;

    }
    public String getRatingString()
    {
        String rString="☆☆☆☆☆";
        StringBuilder rsb =  new StringBuilder(rString);

        for (int i = 0; i < rating; i++)
        {

            rsb.setCharAt(i,'★');
        }
        rString=rsb.toString();
        return rString;
    }

}
