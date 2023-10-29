package com.hodvidar.carrace.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@Getter
@Setter
public class ParticipantDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private RaceDao race;

    // TODO use another object / table to map the relationship:
    //    participant (name) - participation to a race (with a number) - race
    //    ex
    /*
        @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<RaceParticipation> participattions;
    */
}
