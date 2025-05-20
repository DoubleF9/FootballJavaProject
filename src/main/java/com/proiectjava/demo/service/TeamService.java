package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.*;
import com.proiectjava.demo.model.*;
import com.proiectjava.demo.repository.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class TeamService {
    private final static Logger log = LoggerFactory.getLogger(TeamService.class);
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
                    manager.setTrophiesWon(dto.getTrophiesWon());
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
        ManagerDto managerDto = new ManagerDto();
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setCountry(manager.getCountry());
        managerDto.setSalary(manager.getSalary());
        managerDto.setTrophiesWon(manager.getTrophiesWon());
        return managerDto;
    }

    private OwnerDto convertOwnerToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setFirstName(owner.getFirstName());
        ownerDto.setLastName(owner.getLastName());
        ownerDto.setDateOfBirth(owner.getDateOfBirth());
        ownerDto.setCountry(owner.getCountry());
        ownerDto.setNetWorth(owner.getNetWorth());
        return ownerDto;
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

    public TeamDto addTeamWithId(TeamWithIdDto dto) {
        // Check if team with identical properties already exists
        List<Team> existingTeams = teamRepository.findByName(dto.getName())
                .map(List::of)
                .orElse(List.of());

        for (Team team : existingTeams) {
            // Check if all fields match
            boolean leagueMatches = (dto.getLeagueId() == null && team.getLeague() == null) ||
                    (team.getLeague() != null && dto.getLeagueId() != null &&
                            team.getLeague().getId().equals(dto.getLeagueId()));

            boolean stadiumMatches = (dto.getStadiumId() == null && team.getStadium() == null) ||
                    (team.getStadium() != null && dto.getStadiumId() != null &&
                            team.getStadium().getId().equals(dto.getStadiumId()));

            boolean managerMatches = (dto.getManagerId() == null && team.getManager() == null) ||
                    (team.getManager() != null && dto.getManagerId() != null &&
                            team.getManager().getId().equals(dto.getManagerId()));

            boolean ownerMatches = (dto.getOwnerId() == null && team.getOwner() == null) ||
                    (team.getOwner() != null && dto.getOwnerId() != null &&
                            team.getOwner().getId().equals(dto.getOwnerId()));

            if (leagueMatches && stadiumMatches && managerMatches && ownerMatches) {
                return convertToDto(team);
            }
        }

        // Create new team if not found
        Team team = new Team();
        team.setName(dto.getName());

        // Handle related entities
        if (dto.getLeagueId() != null) {
            League league = leagueRepository.findById(dto.getLeagueId()).orElse(null);
            team.setLeague(league);
        }
        if (dto.getStadiumId() != null) {
            Stadium stadium = stadiumRepository.findById(dto.getStadiumId()).orElse(null);
            team.setStadium(stadium);
        }
        if (dto.getManagerId() != null) {
            Manager manager = managerRepository.findById(dto.getManagerId()).orElse(null);
            team.setManager(manager);
        }
        if (dto.getOwnerId() != null) {
            Owner owner = ownerRepository.findById(dto.getOwnerId()).orElse(null);
            team.setOwner(owner);
        }

        return convertToDto(teamRepository.save(team));
    }

    public TeamDto findById(Integer id) {
        return teamRepository.findById(id)
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

    public TeamDto updateWithId(TeamWithIdDto dto) {
        log.info("Updating team with ID: {}", dto.getId());
        return teamRepository.findById(dto.getId())
                .map(existingTeam -> {
                    if (dto.getName() != null) {
                        existingTeam.setName(dto.getName());
                    }

                    if (dto.getLeagueId() != null) {
                        Optional<League> league = leagueRepository.findById(dto.getLeagueId());
                        if(league.isPresent()) {
                            existingTeam.setLeague(league.get());
                        }
                        else{
                            log.info("League with leagueId=[{}] not found.", dto.getLeagueId());
                        }
                    }

                    if (dto.getStadiumId() != null) {
                        Optional<Stadium> stadium = stadiumRepository.findById(dto.getStadiumId());
                        if(stadium.isPresent()) {
                            existingTeam.setStadium(stadium.get());
                        }
                        else{
                            log.info("Stadium with stadiumId=[{}] not found.", dto.getStadiumId());
                        }
                    }

                    if (dto.getManagerId() != null) {
                        Optional<Manager> manager = managerRepository.findById(dto.getManagerId());
                        if(manager.isPresent()) {
                            existingTeam.setManager(manager.get());
                        }
                        else{
                            log.info("Manager with managerId=[{}] not found.", dto.getManagerId());
                        }
                    }

                    if (dto.getOwnerId() != null) {
                        Optional<Owner> owner = ownerRepository.findById(dto.getOwnerId());
                        if(owner.isPresent()) {
                            existingTeam.setOwner(owner.get());
                        }
                        else{
                            log.info("Owner with ownerId=[{}] not found.", dto.getOwnerId());
                        }
                    }
                    log.info("Updating team with ID: {} completed", dto.getId());
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