package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Stadium;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
    @Query(value="SELECT * FROM stadium s WHERE s.name = ?1 ORDER BY ID DESC LIMIT 1",
            nativeQuery = true)
    Optional<Stadium> findByName(String name);
}
