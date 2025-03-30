package Model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tour {


    private String name;
    private String from;
    private String to;
    private Double distance;
    private String estimatedTime;
    private String description;
    private String id;
    //create additional "image" variable later

    public Tour(String name, String from, String to, Double distance, String estimatedTime, String description, String id) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.description = description;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }


}
