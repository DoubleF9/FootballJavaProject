package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.AddPlayerToTeamDto;
import com.proiectjava.demo.dto.PlayerDto;
import com.proiectjava.demo.dto.TeamDto;
import com.proiectjava.demo.model.*;
import com.proiectjava.demo.repository.PlayerRepository;
import com.proiectjava.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    private Team teamDtoToTeam(TeamDto teamDto) {
        if (teamDto == null) {
            return null;
        }

        Team team = new Team();
        team.setName(teamDto.getName());

        // Convert League
        if (teamDto.getLeague() != null) {
            League league = new League();
            league.setName(teamDto.getLeague().getName());
            league.setCountry(teamDto.getLeague().getCountry());
            team.setLeague(league);
        }

        // Convert Stadium
        if (teamDto.getStadium() != null) {
            Stadium stadium = new Stadium();
            stadium.setName(teamDto.getStadium().getName());
            stadium.setCountry(teamDto.getStadium().getCountry());
            stadium.setCapacity(teamDto.getStadium().getCapacity());
            team.setStadium(stadium);
        }

        // Convert Manager
        if (teamDto.getManager() != null) {
            Manager manager = new Manager();
            manager.setFirstName(teamDto.getManager().getFirstName());
            manager.setLastName(teamDto.getManager().getLastName());
            manager.setSalary(teamDto.getManager().getSalary());
            manager.setDateOfBirth(teamDto.getManager().getDateOfBirth());
            manager.setCountry(teamDto.getManager().getCountry());
            team.setManager(manager);
        }

        // Convert Owner
        if (teamDto.getOwner() != null) {
            Owner owner = new Owner();
            owner.setFirstName(teamDto.getOwner().getFirstName());
            owner.setLastName(teamDto.getOwner().getLastName());
            owner.setNetWorth(teamDto.getOwner().getNetWorth());
            owner.setDateOfBirth(teamDto.getOwner().getDateOfBirth());
            owner.setCountry(teamDto.getOwner().getCountry());
            team.setOwner(owner);
        }

        return team;
    }
    // Convert DTO to Entity
    public Player convertToEntity(PlayerDto playerDto) {
        Player player = new Player();
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setSalary(playerDto.getSalary());
        player.setDateOfBirth(playerDto.getDateOfBirth());
        player.setCountry(playerDto.getCountry());
        player.setNumber(playerDto.getNumber());
        player.setGoals(playerDto.getGoals());
        player.setAssists(playerDto.getAssists());
        player.setPosition(playerDto.getPosition());

        return player;
    }

    // Convert Entity to DTO
    public PlayerDto convertToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setFirstName(player.getFirstName());
        playerDto.setLastName(player.getLastName());
        playerDto.setSalary(player.getSalary());
        playerDto.setDateOfBirth(player.getDateOfBirth());
        playerDto.setCountry(player.getCountry());
        playerDto.setNumber(player.getNumber());
        playerDto.setGoals(player.getGoals());
        playerDto.setAssists(player.getAssists());
        playerDto.setPosition(player.getPosition());

        return playerDto;
    }

    public Player addPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setSalary(playerDto.getSalary());
        player.setDateOfBirth(playerDto.getDateOfBirth());
        player.setCountry(playerDto.getCountry());
        player.setNumber(playerDto.getNumber());
        player.setGoals(playerDto.getGoals()==null ? 0 : playerDto.getGoals());
        player.setAssists(playerDto.getAssists()==null ? 0 : playerDto.getAssists());
        player.setPosition(playerDto.getPosition());

        return playerRepository.save(player);
    }

    public String addPlayerToTeam(AddPlayerToTeamDto addPlayerToTeam) {
        Integer playerId = addPlayerToTeam.getPlayerId();
        Integer teamId = addPlayerToTeam.getTeamId();
        Player player = playerRepository.findById(playerId).orElse(null);
        Team team = teamRepository.findById(teamId).orElse(null);

        if (player == null || team == null) {
            return "Player or Team not found";
        }

        player.setTeam(team);
        playerRepository.save(player);
        return "Player added to team successfully";
    }
    public PlayerDto getPlayerById(Integer id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            return null;
        }
        return convertToDto(player);
    }
    public List<PlayerDto> findAllByTeamId(Integer teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if (team == null) {
            return List.of(); // Return empty list if team doesn't exist
        }
        List<Player> players = playerRepository.findAllByTeam(Optional.of(team));

        return players.stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(PlayerDto::getGoals)
                        .reversed())
                .toList();

    }

    public PlayerDto updatePlayer(Integer id, PlayerDto playerDto) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            return null;
        }
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setSalary(playerDto.getSalary());
        player.setDateOfBirth(playerDto.getDateOfBirth());
        player.setCountry(playerDto.getCountry());
        player.setNumber(playerDto.getNumber());
        player.setGoals(playerDto.getGoals());
        player.setAssists(playerDto.getAssists());
        player.setPosition(playerDto.getPosition());

        return convertToDto(playerRepository.save(player));
    }
    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }

}
