package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.TeamDto;
import com.proiectjava.demo.service.TeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @PostMapping("/add")
    public TeamDto addTeam(@RequestBody TeamDto team) {
        return teamService.addTeam(team);
    }

    @GetMapping("/get/{teamId}")
    public TeamDto getTeam(@PathVariable Integer teamId) {
        return teamService.findById(teamId);
    }

    @PutMapping("/update/{teamId}")
    public TeamDto updateTeam(@PathVariable Integer teamId,@RequestBody TeamDto team) {
        return teamService.updateTeam(teamId, team);
    }
    @DeleteMapping("/delete/{teamId}")
    public void deleteTeam(@PathVariable Integer teamId) {
        teamService.deleteTeam(teamId);
    }
}
