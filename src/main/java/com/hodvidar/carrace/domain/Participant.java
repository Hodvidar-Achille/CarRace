package com.hodvidar.carrace.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class Participant implements Serializable {
    private Long id;
    private String name;
    private Integer number;
    @JsonBackReference
    @ToString.Exclude
    private Race race;
}
