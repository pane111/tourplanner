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



}
