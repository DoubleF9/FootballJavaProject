package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.AddPlayerToTeamDto;
import com.proiectjava.demo.dto.PlayerDto;
import com.proiectjava.demo.model.Player;
import com.proiectjava.demo.repository.PlayerRepository;
import com.proiectjava.demo.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @PostMapping("/add")
    public Player addPlayer(@RequestBody PlayerDto player) {
        return playerService.addPlayer(player);
    }
    @PutMapping("/addPlayerToTeam")
    public String addPlayerToTeam(@RequestBody AddPlayerToTeamDto addPlayerToTeam) {
        return playerService.addPlayerToTeam(addPlayerToTeam);
    }
    @GetMapping("/getPlayer/{id}")
    public PlayerDto getPlayer(@PathVariable Integer id) {
        return playerService.getPlayerById(id);
    }
    @PutMapping("/update/{id}")
    public PlayerDto updatePlayer(@PathVariable Integer id, @RequestBody PlayerDto player) {
        return playerService.updatePlayer(id, player);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePlayer( @PathVariable Integer id) {
        playerService.deletePlayer(id);
    }
    @GetMapping("/findAllByTeam/{teamId}")
    public List<PlayerDto> findAllByTeam(@PathVariable Integer teamId) {
        return playerService.findAllByTeamId(teamId);
    }



}
