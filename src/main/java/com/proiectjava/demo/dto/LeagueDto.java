package com.proiectjava.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueDto {
    private String name;
    private String country;

}