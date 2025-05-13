package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "referee")
@Data
public class Referee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Column(name = "SALARY")
    private Integer salary;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "COUNTRY", length = 100)
    private String country;

    public Referee() {

    }
}