package com.hodvidar.carrace.service;

import com.hodvidar.carrace.mapper.RaceMapper;
import com.hodvidar.carrace.validation.RaceValidationException;
import com.hodvidar.carrace.validation.RaceValidator;
import com.hodvidar.carrace.domain.Race;
import com.hodvidar.carrace.repository.RaceRepository;
import com.hodvidar.carrace.event.RaceCreationProducer;
import com.hodvidar.carrace.dao.RaceDao;
import com.hodvidar.carrace.dto.RaceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;
    private final RaceValidator raceValidator;

    private final RaceCreationProducer producer;
    public static final String TOPIC = "race_created";

    @Autowired
    public RaceServiceImpl(final RaceRepository raceRepository,
                           final RaceMapper raceMapper,
                           final RaceValidator raceValidator,
                           final RaceCreationProducer producer) {
        this.raceRepository = raceRepository;
        this.raceMapper = raceMapper;
        this.raceValidator = raceValidator;
        this.producer = producer;
    }

    @Override
    public Race createRace(final RaceDto dto) throws RaceValidationException {
        Race newRace = raceMapper.dtoToModel(dto);
        raceValidator.validateRace(newRace);
        final RaceDao save = raceRepository.save(raceMapper.modelToEntity(newRace));
        final Race savedRace = raceMapper.entityToModel(save);
        log.info("Create a new Race: {}", savedRace);
        notifyKafka(savedRace);
        return savedRace;
    }

    private void notifyKafka(final Race savedRace) {
        producer.send(savedRace);
    }

    @Override
    public List<Race> getAllRaces() {
        return raceRepository.findAll().stream()
                .map(raceMapper::entityToModel)
                .toList();
    }

    @Override
    public Race getRaceById(final Long id) {
        Optional<RaceDao> raceDao = raceRepository.findById(id);
        return raceDao.map(raceMapper::entityToModel).orElse(null);
    }
}
