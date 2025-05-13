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

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Integer id;

    @Column(name = "FIRST_NAME", length = 30)
    protected String firstName;

    @Column(name = "LAST_NAME", length = 30)
    protected String lastName;

    @Column(name = "NET_WORTH")
    protected Integer netWorth;

    @Column(name = "DATE_OF_BIRTH")
    protected LocalDate dateOfBirth;

    @Column(name = "COUNTRY", length = 100)
    protected String country;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    @Nullable
    protected Team team;


    public Player() {

    }
}