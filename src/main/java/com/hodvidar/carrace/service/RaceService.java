package com.hodvidar.carrace.service;

import com.hodvidar.carrace.domain.Race;
import com.hodvidar.carrace.dto.RaceDto;
import com.hodvidar.carrace.validation.RaceValidationException;

import java.util.List;

public interface RaceService {

    Race createRace(RaceDto raceDao) throws RaceValidationException;

    List<Race> getAllRaces();

    Race getRaceById(Long id);
}
