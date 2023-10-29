package com.hodvidar.carrace.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Race implements Serializable {

    private Long id;
    private String location;
    private LocalDate date;
    private Integer uniqueNumber;
    @JsonManagedReference
    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setRace(this);
    }
}
