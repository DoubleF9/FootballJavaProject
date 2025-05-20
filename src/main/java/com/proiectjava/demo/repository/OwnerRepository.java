package com.proiectjava.demo.repository;

import com.proiectjava.demo.model.Owner;
import com.proiectjava.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query(value="SELECT * FROM owner o WHERE o.first_name = ?1 AND o.last_name = ?2 LIMIT 1",
            nativeQuery = true)
    Optional<Owner> findByFirstNameAndLastName(String firstName, String lastName);
}
