package com.hodvidar.carrace.mapper;

import com.hodvidar.carrace.domain.Race;
import com.hodvidar.carrace.dao.RaceDao;
import com.hodvidar.carrace.dto.RaceDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = ParticipantMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface RaceMapper {

    Race dtoToModel(RaceDto dto);

    Race entityToModel(RaceDao entity);

    RaceDto modelToDto(Race model);

    RaceDao modelToEntity(Race model);
}
