package com.hodvidar.carrace.config;

import com.hodvidar.carrace.mapper.ParticipantMapper;
import com.hodvidar.carrace.mapper.RaceMapper;
import com.hodvidar.carrace.mapper.RaceMapperImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public RaceMapper raceMapper() {
        return new RaceMapperImpl(Mappers.getMapper(ParticipantMapper.class));
    }
}
