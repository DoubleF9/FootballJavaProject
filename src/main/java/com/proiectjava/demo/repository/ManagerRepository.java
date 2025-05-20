package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Manager;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    @Query(value="SELECT * FROM manager m WHERE m.first_name = ?1 AND m.last_name = ?2 ORDER BY ID DESC LIMIT 1",
            nativeQuery = true)
    Optional<Manager> findByFirstNameAndLastName(String firstName, String lastName);
}
