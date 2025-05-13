package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.ManagerDto;
import com.proiectjava.demo.model.Country;
import com.proiectjava.demo.model.Manager;
import com.proiectjava.demo.model.Team;
import com.proiectjava.demo.repository.ManagerRepository;
import com.proiectjava.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final TeamRepository teamRepository;

    public ManagerService(ManagerRepository managerRepository, TeamRepository teamRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
    }

    // Convert DTO to entity
    public Manager convertToEntity(ManagerDto dto) {
        if (dto == null) return null;

        Optional<Manager> existingManager = managerRepository.findByFirstNameAndLastName(
                dto.getFirstName(), dto.getLastName());

        if (existingManager.isPresent()) {
            Manager manager = existingManager.get();
            // Update existing manager properties
            manager.setSalary(dto.getSalary());
            manager.setDateOfBirth(dto.getDateOfBirth());
            manager.setCountry(String.valueOf(dto.getCountry()));
            manager.setTrophiesWon(dto.getTrophiesWon());
            return manager;
        }

        // Create new manager
        Manager manager = new Manager();
        manager.setFirstName(dto.getFirstName());
        manager.setLastName(dto.getLastName());
        manager.setSalary(dto.getSalary());
        manager.setDateOfBirth(dto.getDateOfBirth());
        manager.setCountry(String.valueOf(dto.getCountry()));
        manager.setTrophiesWon(dto.getTrophiesWon());

        return manager;
    }

    // Convert entity to DTO
    public ManagerDto convertToDto(Manager manager) {
        if (manager == null) return null;


        ManagerDto managerDto = new ManagerDto();
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setCountry(String.valueOf(manager.getCountry()));
        managerDto.setSalary(manager.getSalary());
        managerDto.setTrophiesWon(manager.getTrophiesWon());
        return managerDto;
    }

    public ManagerDto addManager(ManagerDto managerDto, Integer teamId) {
        // Convert DTO to entity and save it first
        Manager manager = convertToEntity(managerDto);
        manager = managerRepository.save(manager);

        // Now associate it with the team
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        team.setManager(manager);
        teamRepository.save(team);

        return convertToDto(manager);
    }

    public ManagerDto changeManager(ManagerDto managerDto, Integer teamId) {
        // Convert DTO to entity and save it first
        Manager manager = convertToEntity(managerDto);
        manager = managerRepository.save(manager);

        // Now associate it with the team
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        team.setManager(manager);
        teamRepository.save(team);

        return convertToDto(manager);
    }

    public List<ManagerDto> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ManagerDto findById(Integer id) {
        return managerRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }
    public ManagerDto addManagerWithoutTeam(ManagerDto managerDto) {
        Manager manager = convertToEntity(managerDto);
        manager = managerRepository.save(manager);
        return managerDto;
    }
    public void deleteManager(Integer id) {
        managerRepository.deleteById(id);
    }
}