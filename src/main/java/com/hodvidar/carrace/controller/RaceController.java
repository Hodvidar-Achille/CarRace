package com.hodvidar.carrace.controller;

import com.hodvidar.carrace.mapper.RaceMapper;
import com.hodvidar.carrace.service.RaceService;
import com.hodvidar.carrace.dto.RaceDto;
import com.hodvidar.carrace.validation.RaceValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    private final RaceService raceService;

    private final RaceMapper raceMapper;

    @Autowired
    private RaceController(final RaceService raceService,
                           final RaceMapper raceMapper) {
        this.raceService = raceService;
        this.raceMapper = raceMapper;
    }

    @GetMapping
    public List<RaceDto> getAllRaces() {
        return raceService.getAllRaces().stream()
                .map(raceMapper::modelToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public RaceDto getRace(@PathVariable Long id) {
        return raceMapper.modelToDto(raceService.getRaceById(id));
    }

    @PostMapping
    public ResponseEntity<RaceDto> createRace(@Valid @RequestBody RaceDto race) throws RaceValidationException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(raceMapper.modelToDto(raceService.createRace(race)));
    }
}

