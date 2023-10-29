package com.hodvidar.carrace.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "races")
@NoArgsConstructor
@Getter
@Setter
public class RaceDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private LocalDate date;

    @Column(name = "unique_number")
    private Integer uniqueNumber;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantDao> participants = new ArrayList<>();

    public void addParticipant(ParticipantDao participant) {
        participants.add(participant);
        participant.setRace(this);
    }

    // TODO (for future version) use another object / table to map the relationship:
    //    participant (name) - participation to a race (with a number) - race
    //    ex
    /*
        @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<RaceParticipation> participants;
    */
}
