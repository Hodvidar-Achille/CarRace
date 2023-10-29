package com.hodvidar.carrace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodvidar.carrace.config.TestConfig;
import com.hodvidar.carrace.controller.RaceController;
import com.hodvidar.carrace.domain.Participant;
import com.hodvidar.carrace.domain.Race;
import com.hodvidar.carrace.service.RaceService;
import com.hodvidar.carrace.dto.ParticipantDto;
import com.hodvidar.carrace.dto.RaceDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RaceController.class)
@WithMockUser
@Import(TestConfig.class)
@ActiveProfiles("test")
class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceService raceService;


    @Test
    void getAllRaces() throws Exception {
        Participant mockedParticipant = new Participant();
        mockedParticipant.setId(10L);
        mockedParticipant.setName("Test");
        mockedParticipant.setNumber(1);
        Race mockedRace = new Race();
        mockedRace.setId(100L);
        mockedRace.setLocation("Paris");
        mockedRace.setDate(LocalDate.of(2023, 10, 28));
        mockedRace.setUniqueNumber(115);
        mockedRace.addParticipant(mockedParticipant);

        Participant mockedParticipant2 = new Participant();
        mockedParticipant2.setId(20L);
        mockedParticipant2.setName("Test2");
        mockedParticipant2.setNumber(1);
        Race mockedRace2 = new Race();
        mockedRace2.setId(200L);
        mockedRace2.setLocation("London");
        mockedRace2.setDate(LocalDate.of(2023, 10, 29));
        mockedRace2.setUniqueNumber(225);
        mockedRace2.addParticipant(mockedParticipant2);

        when(raceService.getAllRaces()).thenReturn(List.of(mockedRace, mockedRace2));

        mockMvc.perform(get("/api/races"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(100L))
                .andExpect(jsonPath("$[0].location").value("Paris"))
                .andExpect(jsonPath("$[0].date").value("2023-10-28"))
                .andExpect(jsonPath("$[0].uniqueNumber").value(115))
                .andExpect(jsonPath("$[0].participants[0].id").value(10L))
                .andExpect(jsonPath("$[0].participants[0].name").value("Test"))
                .andExpect(jsonPath("$[0].participants[0].number").value(1))
                .andExpect(jsonPath("$[1].id").value(200L))
                .andExpect(jsonPath("$[1].location").value("London"))
                .andExpect(jsonPath("$[1].date").value("2023-10-29"))
                .andExpect(jsonPath("$[1].uniqueNumber").value(225))
                .andExpect(jsonPath("$[1].participants[0].id").value(20L))
                .andExpect(jsonPath("$[1].participants[0].name").value("Test2"))
                .andExpect(jsonPath("$[1].participants[0].number").value(1));
    }

    @Test
    void getRace() throws Exception {
        Participant mockedParticipant = new Participant();
        mockedParticipant.setId(10L);
        mockedParticipant.setName("Test");
        mockedParticipant.setNumber(1);
        Race mockedRace = new Race();
        mockedRace.setId(100L);
        mockedRace.setLocation("Paris");
        mockedRace.setDate(LocalDate.of(2023, 10, 28));
        mockedRace.setUniqueNumber(115);
        mockedRace.addParticipant(mockedParticipant);

        when(raceService.getRaceById(1L)).thenReturn(mockedRace);

        mockMvc.perform(get("/api/races/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.location").value("Paris"))
                .andExpect(jsonPath("$.date").value("2023-10-28"))
                .andExpect(jsonPath("$.uniqueNumber").value(115))
                .andExpect(jsonPath("$.participants[0].id").value(10L))
                .andExpect(jsonPath("$.participants[0].name").value("Test"))
                .andExpect(jsonPath("$.participants[0].number").value(1));
    }

    @Test
    void createRaceTest() throws Exception {
        ParticipantDto participantDto = new ParticipantDto(null, "Test Participant 1", 1);
        ParticipantDto participantDto2 = new ParticipantDto(null, "Test Participant 2", 2);
        ParticipantDto participantDto3 = new ParticipantDto(null, "Test Participant 3", 3);
        RaceDto raceToCreate = new RaceDto(null, "Monaco", "2023-11-15", 101,
                List.of(participantDto, participantDto2, participantDto3));

        Race createdRace = new Race();
        createdRace.setId(1L);
        createdRace.setLocation("Monaco");
        createdRace.setDate(LocalDate.of(2023, 11, 15));
        createdRace.setUniqueNumber(101);
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setName("Test Participant 1");
        participant.setNumber(1);
        Participant participant2 = new Participant();
        participant2.setId(2L);
        participant2.setName("Test Participant 2");
        participant2.setNumber(2);
        Participant participant3 = new Participant();
        participant3.setId(3L);
        participant3.setName("Test Participant 3");
        participant3.setNumber(3);
        createdRace.addParticipant(participant);
        createdRace.addParticipant(participant2);
        createdRace.addParticipant(participant3);

        when(raceService.createRace(ArgumentMatchers.any(RaceDto.class))).thenReturn(createdRace);

        String raceJson = new ObjectMapper().writeValueAsString(raceToCreate);

        mockMvc.perform(post("/api/races").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(raceJson))
                .andExpect(status().isCreated()) // or whatever status you expect
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.location").value("Monaco"))
                .andExpect(jsonPath("$.date").value("2023-11-15"))
                .andExpect(jsonPath("$.uniqueNumber").value(101))
                .andExpect(jsonPath("$.participants[0].id").value(1L))
                .andExpect(jsonPath("$.participants[0].name").value("Test Participant 1"))
                .andExpect(jsonPath("$.participants[0].number").value(1))
                .andExpect(jsonPath("$.participants[1].id").value(2L))
                .andExpect(jsonPath("$.participants[1].name").value("Test Participant 2"))
                .andExpect(jsonPath("$.participants[1].number").value(2))
                .andExpect(jsonPath("$.participants[2].id").value(3L))
                .andExpect(jsonPath("$.participants[2].name").value("Test Participant 3"))
                .andExpect(jsonPath("$.participants[2].number").value(3));
    }

}