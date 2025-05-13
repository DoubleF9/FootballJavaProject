package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Owner;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {


    Optional<Owner> findByFirstNameAndLastName(String firstName, String lastName);
}
