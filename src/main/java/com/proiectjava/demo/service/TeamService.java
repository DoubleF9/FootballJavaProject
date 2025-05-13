package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.*;
import com.proiectjava.demo.model.*;
import com.proiectjava.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final StadiumRepository stadiumRepository;
    private final ManagerRepository managerRepository;
    private final OwnerRepository ownerRepository;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository,
                       LeagueRepository leagueRepository,
                       StadiumRepository stadiumRepository,
                       ManagerRepository managerRepository,
                       OwnerRepository ownerRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
        this.stadiumRepository = stadiumRepository;
        this.managerRepository = managerRepository;
        this.ownerRepository = ownerRepository;
        this.playerRepository = playerRepository;
    }

    // DTO -> Entity converters
    public Team convertToEntity(TeamDto dto) {
        if (dto == null) return null;

        // First check if team already exists
        Optional<Team> existingTeam = teamRepository.findByName(dto.getName());
        if (existingTeam.isPresent()) {
            return existingTeam.get();
        }

        // Create new team if not found
        Team team = new Team();
        team.setName(dto.getName());

        // Handle related entities
        if (dto.getLeague() != null) {
            team.setLeague(convertLeagueToEntity(dto.getLeague()));
        }
        if (dto.getStadium() != null) {
            team.setStadium(convertStadiumToEntity(dto.getStadium()));
        }
        if (dto.getManager() != null) {
            team.setManager(convertManagerToEntity(dto.getManager()));
        }
        if (dto.getOwner() != null) {
            team.setOwner(convertOwnerToEntity(dto.getOwner()));
        }

        return team;
    }

    private League convertLeagueToEntity(LeagueDto dto) {
        if (dto == null) return null;

        return leagueRepository.findByName(dto.getName())
                .orElseGet(() -> {
                    League league = new League();
                    league.setName(dto.getName());
                    league.setCountry(dto.getCountry());
                    return leagueRepository.save(league);
                });
    }

    private Stadium convertStadiumToEntity(StadiumDto dto) {
        if (dto == null) return null;

        return stadiumRepository.findByName(dto.getName())
                .orElseGet(() -> {
                    Stadium stadium = new Stadium();
                    stadium.setName(dto.getName());
                    stadium.setCountry(dto.getCountry());
                    stadium.setCapacity(dto.getCapacity());
                    return stadiumRepository.save(stadium);
                });
    }

    private Manager convertManagerToEntity(ManagerDto dto) {
        if (dto == null) return null;

        return managerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName())
                .orElseGet(() -> {
                    Manager manager = new Manager();
                    manager.setFirstName(dto.getFirstName());
                    manager.setLastName(dto.getLastName());
                    manager.setDateOfBirth(dto.getDateOfBirth());
                    manager.setCountry(dto.getCountry());
                    manager.setSalary(dto.getSalary());
                    return managerRepository.save(manager);
                });
    }

    private Owner convertOwnerToEntity(OwnerDto dto) {
        if (dto == null) return null;

        return ownerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName())
                .orElseGet(() -> {
                    Owner owner = new Owner();
                    owner.setFirstName(dto.getFirstName());
                    owner.setLastName(dto.getLastName());
                    owner.setDateOfBirth(dto.getDateOfBirth());
                    owner.setCountry(dto.getCountry());
                    owner.setNetWorth(dto.getNetWorth());
                    return ownerRepository.save(owner);
                });
    }

    // Entity -> DTO converters
    public TeamDto convertToDto(Team team) {
        if (team == null) return null;

        return TeamDto.builder()
                .name(team.getName())
                .league(team.getLeague() != null ? convertLeagueToDto(team.getLeague()) : null)
                .stadium(team.getStadium() != null ? convertStadiumToDto(team.getStadium()) : null)
                .manager(team.getManager() != null ? convertManagerToDto(team.getManager()) : null)
                .owner(team.getOwner() != null ? convertOwnerToDto(team.getOwner()) : null)
                .build();
    }

    private LeagueDto convertLeagueToDto(League league) {
        return LeagueDto.builder()
                .name(league.getName())
                .country(league.getCountry())
                .build();
    }

    private StadiumDto convertStadiumToDto(Stadium stadium) {
        return StadiumDto.builder()
                .name(stadium.getName())
                .country(stadium.getCountry())
                .capacity(stadium.getCapacity())
                .build();
    }

    private ManagerDto convertManagerToDto(Manager manager) {
        return ManagerDto.builder()
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .dateOfBirth(manager.getDateOfBirth())
                .country(manager.getCountry())
                .salary(manager.getSalary())
                .build();
    }

    private OwnerDto convertOwnerToDto(Owner owner) {
        return OwnerDto.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .dateOfBirth(owner.getDateOfBirth())
                .country(owner.getCountry())
                .netWorth(owner.getNetWorth())
                .build();
    }

    // Service methods
    public TeamDto addTeam(TeamDto teamDto) {
        Team team = convertToEntity(teamDto);
        Stadium stadium = convertStadiumToEntity(teamDto.getStadium());
        team.setStadium(stadium);
        Manager manager = convertManagerToEntity(teamDto.getManager());
        team.setManager(manager);
        League league = convertLeagueToEntity(teamDto.getLeague());
        team.setLeague(league);
        Owner owner = convertOwnerToEntity(teamDto.getOwner());
        team.setOwner(owner);
        team = teamRepository.save(team);
        return convertToDto(team);
    }

    public TeamDto findById(Integer id) {
        return teamRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public TeamDto findByName(String name) {
        return teamRepository.findByName(name)
                .map(this::convertToDto)
                .orElse(null);
    }

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TeamDto updateTeam(Integer id, TeamDto teamDto) {
        return teamRepository.findById(id)
                .map(existingTeam -> {
                    if (teamDto.getName() != null) {
                        existingTeam.setName(teamDto.getName());
                    }

                    if (teamDto.getLeague() != null) {
                        existingTeam.setLeague(convertLeagueToEntity(teamDto.getLeague()));
                    }

                    if (teamDto.getStadium() != null) {
                        existingTeam.setStadium(convertStadiumToEntity(teamDto.getStadium()));
                    }

                    if (teamDto.getManager() != null) {
                        existingTeam.setManager(convertManagerToEntity(teamDto.getManager()));
                    }

                    if (teamDto.getOwner() != null) {
                        existingTeam.setOwner(convertOwnerToEntity(teamDto.getOwner()));
                    }

                    return convertToDto(teamRepository.save(existingTeam));
                })
                .orElse(null);
    }


    public void deleteTeam(Integer id) {
        Optional<Team> team = teamRepository.findById(id);

        // First set team to null for all associated players
        playerRepository.findAllByTeam(team)
                .forEach(player -> {
                            player.setTeam(null);
                            playerRepository.save(player);
                        });

        // Now it's safe to delete the team
        teamRepository.deleteById(id);
    }

}