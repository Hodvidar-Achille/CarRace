package com.hodvidar.carrace.mapper;

import com.hodvidar.carrace.dao.ParticipantDao;
import com.hodvidar.carrace.dao.RaceDao;
import com.hodvidar.carrace.domain.Participant;
import com.hodvidar.carrace.domain.Race;
import com.hodvidar.carrace.dto.ParticipantDto;
import com.hodvidar.carrace.dto.RaceDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-29T23:43:23+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class RaceMapperImpl implements RaceMapper {

    private final ParticipantMapper participantMapper;

    @Autowired
    public RaceMapperImpl(ParticipantMapper participantMapper) {

        this.participantMapper = participantMapper;
    }

    @Override
    public Race dtoToModel(RaceDto dto) {
        if ( dto == null ) {
            return null;
        }

        Race race = new Race();

        race.setId( dto.id() );
        race.setLocation( dto.location() );
        if ( dto.date() != null ) {
            race.setDate( LocalDate.parse( dto.date() ) );
        }
        race.setUniqueNumber( dto.uniqueNumber() );
        if ( dto.participants() != null ) {
            for ( ParticipantDto participant : dto.participants() ) {
                race.addParticipant( participantMapper.dtoToModel( participant ) );
            }
        }

        return race;
    }

    @Override
    public Race entityToModel(RaceDao entity) {
        if ( entity == null ) {
            return null;
        }

        Race race = new Race();

        race.setId( entity.getId() );
        race.setLocation( entity.getLocation() );
        race.setDate( entity.getDate() );
        race.setUniqueNumber( entity.getUniqueNumber() );
        if ( entity.getParticipants() != null ) {
            for ( ParticipantDao participant : entity.getParticipants() ) {
                race.addParticipant( participantMapper.entityToModel( participant ) );
            }
        }

        return race;
    }

    @Override
    public RaceDto modelToDto(Race model) {
        if ( model == null ) {
            return null;
        }

        Long id = null;
        String location = null;
        String date = null;
        Integer uniqueNumber = null;
        List<ParticipantDto> participants = null;

        id = model.getId();
        location = model.getLocation();
        if ( model.getDate() != null ) {
            date = DateTimeFormatter.ISO_LOCAL_DATE.format( model.getDate() );
        }
        uniqueNumber = model.getUniqueNumber();
        participants = participantListToParticipantDtoList( model.getParticipants() );

        RaceDto raceDto = new RaceDto( id, location, date, uniqueNumber, participants );

        return raceDto;
    }

    @Override
    public RaceDao modelToEntity(Race model) {
        if ( model == null ) {
            return null;
        }

        RaceDao raceDao = new RaceDao();

        raceDao.setId( model.getId() );
        raceDao.setLocation( model.getLocation() );
        raceDao.setDate( model.getDate() );
        raceDao.setUniqueNumber( model.getUniqueNumber() );
        if ( model.getParticipants() != null ) {
            for ( Participant participant : model.getParticipants() ) {
                raceDao.addParticipant( participantMapper.modelToEntity( participant ) );
            }
        }

        return raceDao;
    }

    protected List<ParticipantDto> participantListToParticipantDtoList(List<Participant> list) {
        if ( list == null ) {
            return null;
        }

        List<ParticipantDto> list1 = new ArrayList<ParticipantDto>( list.size() );
        for ( Participant participant : list ) {
            list1.add( participantMapper.modelToDto( participant ) );
        }

        return list1;
    }
}
