package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepository extends JpaRepository<Referee, Integer> {
}
