package com.proiectjava.demo.dto;

import lombok.*;

import java.time.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefereeDto {

    private String firstName;
    private String lastName;
    private Integer salary;
    private LocalDate dateOfBirth;
    private String country;

}