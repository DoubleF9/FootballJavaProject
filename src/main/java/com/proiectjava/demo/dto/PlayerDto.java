package com.proiectjava.demo.dto;

import com.proiectjava.demo.model.Team;
import lombok.*;

import java.time.*;

@Data
public class PlayerDto {

    private String firstName;
    private String lastName;
    private Integer netWorth;
    private LocalDate dateOfBirth;
    private String country;

}