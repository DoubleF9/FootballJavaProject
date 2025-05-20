package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query(value="SELECT * FROM team t WHERE t.name = ?1 ORDER BY ID DESC LIMIT 1",
            nativeQuery = true)
    Optional<Team> findByName(String name);

    List<Team> findByStadiumId(Integer id);
}
