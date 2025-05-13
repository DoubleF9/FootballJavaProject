package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "standing")
public class Standing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "SEASON", length = 30)
    private String season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private com.proiectjava.demo.model.Team team;

    @Column(name = "POS")
    private Integer pos;

    @Column(name = "POINTS")
    private Integer points;

    @Column(name = "GOAL_AVG")
    private Integer goalAvg;

}