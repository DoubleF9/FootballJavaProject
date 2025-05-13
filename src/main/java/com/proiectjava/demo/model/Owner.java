package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "owner")
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Column(name = "NET_WORTH")
    private Integer netWorth;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "COUNTRY", length = 100)
    private String country;

}