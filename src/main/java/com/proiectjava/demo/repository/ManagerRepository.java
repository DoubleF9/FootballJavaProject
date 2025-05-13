package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Manager;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Optional<Manager> findByFirstNameAndLastName(String firstName, String lastName);
}
