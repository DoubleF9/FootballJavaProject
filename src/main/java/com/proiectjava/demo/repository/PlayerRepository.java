package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Player;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAllByTeam(Optional<Team> team);
}
