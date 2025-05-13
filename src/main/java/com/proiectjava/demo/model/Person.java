package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Integer id;

    @Column(name = "FIRST_NAME", length = 30)
    protected String firstName;

    @Column(name = "LAST_NAME", length = 30)
    protected String lastName;

    @Column(name = "DATE_OF_BIRTH")
    protected LocalDate dateOfBirth;

    @Column(name = "COUNTRY", length = 100)
    protected String country;
}
