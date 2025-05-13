package com.proiectjava.demo.dto;

import com.proiectjava.demo.model.League;
import com.proiectjava.demo.model.Manager;
import com.proiectjava.demo.model.Owner;
import com.proiectjava.demo.model.Stadium;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private String name;
    private LeagueDto league;
    private StadiumDto stadium;
    private ManagerDto manager;
    private OwnerDto owner;

}