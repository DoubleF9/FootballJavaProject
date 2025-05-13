package com.proiectjava.demo.dto;

import com.proiectjava.demo.model.Person;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;


@Data

@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto{


    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String country;

    private Integer salary;

    private Integer trophiesWon;


}