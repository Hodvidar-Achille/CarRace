package com.hodvidar.carrace.repository;

import com.hodvidar.carrace.dao.RaceDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<RaceDao, Long> {
    // Additional query methods can be defined here if needed
}

