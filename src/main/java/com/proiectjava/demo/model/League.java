package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "league")
@NoArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "COUNTRY", length = 100)
    private String country;
}