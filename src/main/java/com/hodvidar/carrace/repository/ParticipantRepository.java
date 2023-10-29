package com.hodvidar.carrace.repository;

import com.hodvidar.carrace.dao.ParticipantDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<ParticipantDao, Long> {
    // Additional query methods can be defined here if needed
}
