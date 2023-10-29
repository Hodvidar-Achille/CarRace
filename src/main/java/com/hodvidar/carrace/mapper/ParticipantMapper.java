package com.hodvidar.carrace.mapper;

import com.hodvidar.carrace.domain.Participant;
import com.hodvidar.carrace.dao.ParticipantDao;
import com.hodvidar.carrace.dto.ParticipantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    Participant dtoToModel(ParticipantDto dto);

    @Mapping(target = "race", ignore = true)
    Participant entityToModel(ParticipantDao entity);

    @Mapping(target = "race", ignore = true)
    ParticipantDao modelToEntity(Participant model);

    ParticipantDto modelToDto(Participant model);

    Collection<Participant> dtoToModel(Collection<ParticipantDto> dto);

    @Mapping(target = "race", ignore = true)
    List<Participant> entityToModel(Collection<ParticipantDao> entity);

    @Mapping(target = "race", ignore = true)
    Collection<ParticipantDao> modelToEntity(Collection<Participant> model);

    Collection<ParticipantDto> modelToDto(Collection<Participant> model);
}
