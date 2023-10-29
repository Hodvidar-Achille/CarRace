package com.hodvidar.carrace.mapper;

import com.hodvidar.carrace.dao.ParticipantDao;
import com.hodvidar.carrace.domain.Participant;
import com.hodvidar.carrace.dto.ParticipantDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-29T23:43:23+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ParticipantMapperImpl implements ParticipantMapper {

    @Override
    public Participant dtoToModel(ParticipantDto dto) {
        if ( dto == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setId( dto.id() );
        participant.setName( dto.name() );
        participant.setNumber( dto.number() );

        return participant;
    }

    @Override
    public Participant entityToModel(ParticipantDao entity) {
        if ( entity == null ) {
            return null;
        }

        Participant participant = new Participant();

        participant.setId( entity.getId() );
        participant.setName( entity.getName() );
        participant.setNumber( entity.getNumber() );

        return participant;
    }

    @Override
    public ParticipantDao modelToEntity(Participant model) {
        if ( model == null ) {
            return null;
        }

        ParticipantDao participantDao = new ParticipantDao();

        participantDao.setId( model.getId() );
        participantDao.setName( model.getName() );
        participantDao.setNumber( model.getNumber() );

        return participantDao;
    }

    @Override
    public ParticipantDto modelToDto(Participant model) {
        if ( model == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Integer number = null;

        id = model.getId();
        name = model.getName();
        number = model.getNumber();

        ParticipantDto participantDto = new ParticipantDto( id, name, number );

        return participantDto;
    }

    @Override
    public Collection<Participant> dtoToModel(Collection<ParticipantDto> dto) {
        if ( dto == null ) {
            return null;
        }

        Collection<Participant> collection = new ArrayList<Participant>( dto.size() );
        for ( ParticipantDto participantDto : dto ) {
            collection.add( dtoToModel( participantDto ) );
        }

        return collection;
    }

    @Override
    public List<Participant> entityToModel(Collection<ParticipantDao> entity) {
        if ( entity == null ) {
            return null;
        }

        List<Participant> list = new ArrayList<Participant>( entity.size() );
        for ( ParticipantDao participantDao : entity ) {
            list.add( entityToModel( participantDao ) );
        }

        return list;
    }

    @Override
    public Collection<ParticipantDao> modelToEntity(Collection<Participant> model) {
        if ( model == null ) {
            return null;
        }

        Collection<ParticipantDao> collection = new ArrayList<ParticipantDao>( model.size() );
        for ( Participant participant : model ) {
            collection.add( modelToEntity( participant ) );
        }

        return collection;
    }

    @Override
    public Collection<ParticipantDto> modelToDto(Collection<Participant> model) {
        if ( model == null ) {
            return null;
        }

        Collection<ParticipantDto> collection = new ArrayList<ParticipantDto>( model.size() );
        for ( Participant participant : model ) {
            collection.add( modelToDto( participant ) );
        }

        return collection;
    }
}
