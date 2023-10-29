package com.hodvidar.carrace.service;


import com.hodvidar.carrace.dao.ParticipantDao;
import com.hodvidar.carrace.dao.RaceDao;
import com.hodvidar.carrace.domain.Race;
import com.hodvidar.carrace.dto.ParticipantDto;
import com.hodvidar.carrace.dto.RaceDto;
import com.hodvidar.carrace.event.RaceCreationProducer;
import com.hodvidar.carrace.mapper.ParticipantMapper;
import com.hodvidar.carrace.mapper.RaceMapper;
import com.hodvidar.carrace.mapper.RaceMapperImpl;
import com.hodvidar.carrace.repository.RaceRepository;
import com.hodvidar.carrace.validation.RaceValidationException;
import com.hodvidar.carrace.validation.RaceValidator;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
class RaceServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @Spy
    private RaceMapper raceMapper = new RaceMapperImpl(Mappers.getMapper(ParticipantMapper.class));

    @Mock
    private RaceValidator raceValidator;

    @Mock
    private RaceCreationProducer producer;
    @InjectMocks
    private RaceServiceImpl raceService;

    @Test
    void createRace() throws RaceValidationException {
        ParticipantDao mockedParticipant = new ParticipantDao();
        mockedParticipant.setId(1L);
        mockedParticipant.setName("Test");
        mockedParticipant.setNumber(1);
        RaceDao mockedRace = new RaceDao();
        mockedRace.setId(1L);
        mockedRace.setLocation("Paris");
        mockedRace.setDate(LocalDate.now());
        mockedRace.setUniqueNumber(1);
        mockedRace.addParticipant(mockedParticipant);
        when(raceRepository.save(ArgumentMatchers.any(RaceDao.class))).thenReturn(mockedRace);
        Race race = raceService.createRace(new RaceDto(null,
                "Whatever",
                "2023-10-28",
                1,
                List.of(new ParticipantDto(null, "Test", 1))));


        assertNotNull(race);
        assertEquals("Paris", race.getLocation());
        assertEquals("Test", race.getParticipants().get(0).getName());
        assertEquals("Paris", race.getParticipants().get(0).getRace().getLocation());
        verify(raceValidator, times(1)).validateRace(any(Race.class));
        verify(producer, times(1)).send(race);
    }

    @Test
    void getAllRaces() {
        ParticipantDao mockedParticipant = new ParticipantDao();
        mockedParticipant.setId(1L);
        mockedParticipant.setName("Test");
        mockedParticipant.setNumber(1);
        RaceDao mockedRace = new RaceDao();
        mockedRace.setId(1L);
        mockedRace.setLocation("Paris");
        mockedRace.setDate(LocalDate.now());
        mockedRace.setUniqueNumber(1);
        mockedRace.addParticipant(mockedParticipant);
        ParticipantDao mockedParticipant2 = new ParticipantDao();
        mockedParticipant2.setId(2L);
        mockedParticipant2.setName("Test2");
        mockedParticipant2.setNumber(2);
        RaceDao mockedRace2 = new RaceDao();
        mockedRace2.setId(2L);
        mockedRace2.setLocation("London");
        mockedRace2.setDate(LocalDate.now());
        mockedRace2.setUniqueNumber(2);
        mockedRace2.addParticipant(mockedParticipant2);

        when(raceRepository.findAll()).thenReturn(List.of(mockedRace, mockedRace2));
        List<Race> races = raceService.getAllRaces();

        assertNotNull(races);

        assertEquals("Paris", races.get(0).getLocation());
        assertEquals("Test", races.get(0).getParticipants().get(0).getName());
        assertEquals("Paris", races.get(0).getParticipants().get(0).getRace().getLocation());
        assertEquals("London", races.get(1).getLocation());
        assertEquals("Test2", races.get(1).getParticipants().get(0).getName());
    }

    @Test
    void getRaceById() {
        ParticipantDao mockedParticipant = new ParticipantDao();
        mockedParticipant.setId(1L);
        mockedParticipant.setName("Test");
        mockedParticipant.setNumber(1);
        RaceDao mockedRace = new RaceDao();
        mockedRace.setId(1L);
        mockedRace.setLocation("Paris");
        mockedRace.setDate(LocalDate.now());
        mockedRace.setUniqueNumber(1);
        mockedRace.addParticipant(mockedParticipant);

        when(raceRepository.findById(1L)).thenReturn(Optional.of(mockedRace));
        Race race = raceService.getRaceById(1L);

        assertNotNull(race);
        assertEquals("Paris", race.getLocation());
        assertEquals("Test", race.getParticipants().get(0).getName());
        assertEquals("Paris", race.getParticipants().get(0).getRace().getLocation());
    }
}

