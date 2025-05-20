package com.proiectjava.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamWithIdDto {

    private Integer id;
    private String name;
    private Integer leagueId;
    private Integer stadiumId;
    private Integer managerId;
    private Integer ownerId;


}