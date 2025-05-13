package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.League;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {
    Optional<League> findByName(String name);
}
