package com.proiectjava.demo.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandingDto {

    private String season;
    private TeamDto team;
    private Integer pos;
    private Integer points;
    private Integer goalAvg;

}