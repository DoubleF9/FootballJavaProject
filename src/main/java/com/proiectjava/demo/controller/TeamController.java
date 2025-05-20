package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.TeamDto;
import com.proiectjava.demo.dto.TeamWithIdDto;
import com.proiectjava.demo.service.TeamService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/addWithId")
    public TeamDto addTeamWithId(@RequestBody TeamWithIdDto team) {
        return teamService.addTeamWithId(team);
    }

    @GetMapping("/get/{teamId}")
    public TeamDto getTeam(@PathVariable Integer teamId) {
        return teamService.findById(teamId);
    }

    @PutMapping("/update/{teamId}")
    public TeamDto updateTeam(@PathVariable Integer teamId,@RequestBody TeamDto team) {
        return teamService.updateTeam(teamId, team);
    }

    @PutMapping("/updateWithId/{teamId}")
    public ResponseEntity<TeamDto> updateTeamWithId( @RequestBody TeamWithIdDto team) {
        TeamDto dto = teamService.updateWithId(team);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete/{teamId}")
    public void deleteTeam(@PathVariable Integer teamId) {
        teamService.deleteTeam(teamId);
    }

    @GetMapping("/getAll")
    public Iterable<TeamDto> getAllTeams() {
        return teamService.getAllTeams();
    }
}
