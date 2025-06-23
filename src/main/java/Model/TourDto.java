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

    @Override
    public String toString() {
        return name;
    }


}
