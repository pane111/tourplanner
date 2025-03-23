package Model;

import lombok.Getter;
import lombok.Setter;

public class Tour {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String from;
    @Getter
    @Setter
    private String to;
    @Getter
    @Setter
    private Double distance;
    @Getter
    @Setter
    private String estimatedTime;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String id;

    public Tour(String name, String from, String to, Double distance, String estimatedTime, String description, String id) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.description = description;
        this.id = id;
    }


}
