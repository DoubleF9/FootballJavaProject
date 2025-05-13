package com.proiectjava.demo.dto;

import com.proiectjava.demo.model.Person;
import com.proiectjava.demo.model.Positions;
import lombok.*;

import java.time.LocalDate;

@Data
public class PlayerDto  {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String country;

    private Integer salary;
    private Integer number;
    private Integer goals;
    private Integer assists;
    private Positions position;


}