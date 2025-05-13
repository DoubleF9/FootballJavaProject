package com.proiectjava.demo.dto;

import lombok.Data;

@Data
public class AddPlayerToTeamDto {
    private Integer teamId;
    private Integer playerId;
}
