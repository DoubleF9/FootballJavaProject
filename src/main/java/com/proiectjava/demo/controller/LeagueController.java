package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.LeagueDto;
import com.proiectjava.demo.dto.TeamDto;
import com.proiectjava.demo.model.League;
import com.proiectjava.demo.model.Team;
import com.proiectjava.demo.service.LeagueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/league")
public class LeagueController {
    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }
    @GetMapping("/getAllLeaguesWithTeams")
    public HashMap<LeagueDto, List<TeamDto>> getAllLeaguesWithTeams() {
        return leagueService.allLeaguesWithTeams();
    }
    @GetMapping("/printAllLeaguesWithTeams")
    public String printAllLeaguesWithTeams() {
        HashMap<LeagueDto, List<TeamDto>> leagues = leagueService.allLeaguesWithTeams();
        return leagueService.printAllLeaguesWithTeams(leagues);
    }
}
