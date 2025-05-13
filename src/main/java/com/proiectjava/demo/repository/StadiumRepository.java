package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Stadium;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
    Optional<Stadium> findByName(String name);
}
