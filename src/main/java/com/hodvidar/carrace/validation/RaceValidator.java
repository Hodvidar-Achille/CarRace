package com.hodvidar.carrace.validation;

import com.hodvidar.carrace.domain.Participant;
import com.hodvidar.carrace.domain.Race;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceValidator {


    public void validateRace(final Race race) throws RaceValidationException {
        if (null == race) {
            throw new RaceValidationException("Given race is null");
        }
        if (!areParticipantsNumbersValid(race)) {
            throw new RaceValidationException("Participants numbers are not valid, " +
                    "they should start at 1 and be consecutive");
        }
    }

    private boolean areParticipantsNumbersValid(final Race race) {
        if (race == null || race.getParticipants() == null || race.getParticipants().isEmpty()) {
            return false;
        }
        List<Integer> sortedNumbers = race.getParticipants().stream()
                .map(Participant::getNumber)
                .sorted()
                .toList();
        int expectedNumber = 1;
        for (Integer number : sortedNumbers) {
            if (number == null || number != expectedNumber) {
                return false;
            }
            expectedNumber++;
        }
        return true;
    }
}
