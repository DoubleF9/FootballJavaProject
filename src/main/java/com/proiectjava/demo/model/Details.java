package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "position")
@Getter
@Setter
public class Details extends Player {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Integer id;
    @Column(name = "POSITION", length = 30)
    Positions position;
    @Column(name = "NUMBER")
    Integer number;
    @Column(name = "GOALS", length = 30)
    Integer goals;
    @Column(name = "ASSISTS", length = 30)
    Integer assists;
}
