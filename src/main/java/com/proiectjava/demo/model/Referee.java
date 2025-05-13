package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "referee")
@Data
public class Referee extends Person {


    @Column(name = "SALARY")
    private Integer salary;

    @Column(name="isFIFA_REGISTERED")
    private Boolean isFifaRegistered;

    public Referee() {

    }
}