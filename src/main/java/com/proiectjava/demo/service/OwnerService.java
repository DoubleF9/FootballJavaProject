package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.OwnerDto;
import com.proiectjava.demo.model.Owner;
import com.proiectjava.demo.repository.OwnerRepository;
import com.proiectjava.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final TeamRepository teamRepository;

    public OwnerService(OwnerRepository ownerRepository, TeamService teamService, TeamRepository teamRepository) {
        this.ownerRepository = ownerRepository;
        this.teamRepository = teamRepository;
    }
    public OwnerDto add(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setFirstName(ownerDto.getFirstName());
        owner.setLastName(ownerDto.getLastName());
        owner.setCountry(ownerDto.getCountry());
        owner.setDateOfBirth(ownerDto.getDateOfBirth());
        owner.setNetWorth(ownerDto.getNetWorth());
        ownerRepository.save(owner);
        return ownerDto;
    }
    public OwnerDto addOwnerToTeam(OwnerDto ownerDto, String teamName) {
        Owner owner = new Owner();
        owner.setFirstName(ownerDto.getFirstName());
        owner.setLastName(ownerDto.getLastName());
        owner.setCountry(ownerDto.getCountry());
        owner.setDateOfBirth(ownerDto.getDateOfBirth());
        owner.setNetWorth(ownerDto.getNetWorth());
        ownerRepository.save(owner);
        teamRepository.findByName(teamName)
                .ifPresent(team -> {
                    team.setOwner(owner);
                    teamRepository.save(team);
                });
        return ownerDto;
    }

    public OwnerDto updateOwner(OwnerDto ownerDto,Integer id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
        owner.setFirstName(ownerDto.getFirstName());
        owner.setLastName(ownerDto.getLastName());
        owner.setCountry(ownerDto.getCountry());
        owner.setDateOfBirth(ownerDto.getDateOfBirth());
        owner.setNetWorth(ownerDto.getNetWorth());
        ownerRepository.save(owner);
        return ownerDto;

    }

}
