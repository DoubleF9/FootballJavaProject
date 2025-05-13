package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "manager")
@NoArgsConstructor
public class Manager extends Person {

    @Column(name = "SALARY")
    private Integer salary;
    @Column(name="TROPHIES_WON")
    private Integer trophiesWon;


}