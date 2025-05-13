package com.proiectjava.demo.dto;

import com.proiectjava.demo.model.Person;
import lombok.*;

import java.time.LocalDate;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String country;

    private Integer netWorth;

}