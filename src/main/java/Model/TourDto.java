package Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TourDto {


    private String name;

    @JsonProperty("fromLoc")
    private String from;
    @JsonProperty("toLoc")
    private String to;
    private Double distance;
    private String estimatedTime;
    private String description;

    private Long id;

    Coordinate fromCoord;
    Coordinate toCoord;
    //create additional "image" variable later

    public TourDto(String name, String from, String to, Double distance, String estimatedTime, String description, @Nullable Long id) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.description = description;
        if (id != null) {
            this.id = id;
        }
    }
    public TourDto(SendableTour st)
    {
        this.name = st.getName();
        this.id = st.getId();
        this.from = st.getFrom_loc();
        this.to = st.getTo_loc();
        this.distance = st.getDistance();
        this.estimatedTime = st.getEstimated_time();
        this.description = st.getDescription();
        this.fromCoord = new Coordinate(st.getFrom_coords());
        this.toCoord = new Coordinate(st.getTo_coords());
    }

    @Override
    public String toString() {
        return name;
    }


}
