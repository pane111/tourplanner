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
        for (Integer i = 0; i < rating; i++)
        {
            rsb.setCharAt(i,'★');
        }
        return rString;
    }

}
