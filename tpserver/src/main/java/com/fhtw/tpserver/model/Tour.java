package com.fhtw.tpserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name="tours",schema="tp_schema")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("fromLoc")
    private String from_loc;
    @JsonProperty("toLoc")
    private String to_loc;
    @JsonProperty("distance")
    private Double distance;
    @JsonProperty("estimatedTime")
    private String estimated_time;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image")
    private String image;
    @JsonProperty("fromCoords")
    private String from_coords;
    @JsonProperty("toCoords")
    private String to_coords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom_loc() {
        return from_loc;
    }

    public void setFrom_loc(String from_loc) {
        this.from_loc = from_loc;
    }

    public String getTo_loc() {
        return to_loc;
    }

    public void setTo_loc(String to_loc) {
        this.to_loc = to_loc;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(String estimated_time) {
        this.estimated_time = estimated_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFrom_coords() {
        return from_coords;
    }

    public void setFrom_coords(String from_coords) {
        this.from_coords = from_coords;
    }

    public String getTo_coords() {
        return to_coords;
    }

    public void setTo_coords(String to_coords) {
        this.to_coords = to_coords;
    }

}
