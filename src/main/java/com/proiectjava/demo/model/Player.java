package com.proiectjava.demo.model;

import com.proiectjava.demo.dto.TeamDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "player")
@AllArgsConstructor

public class Player extends Person {

    @Column(name = "NET_WORTH")
    protected Integer salary;
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    @Nullable
    protected Team team;

    @Column(name = "POSITION", length = 30)
    Positions position;
    @Column(name = "NUMBER")
    Integer number;
    @Column(name = "GOALS", length = 30)
    Integer goals;
    @Column(name = "ASSISTS", length = 30)
    Integer assists;

    public Player() {

    }
}