package com.proiectjava.demo.dto;

import lombok.*;

import java.time.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private String firstName;
    private String lastName;
    private Integer netWorth;
    private LocalDate dateOfBirth;
    private String country;

}