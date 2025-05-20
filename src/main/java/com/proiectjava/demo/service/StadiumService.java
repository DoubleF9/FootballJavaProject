package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.StadiumDto;
import com.proiectjava.demo.model.Stadium;
import com.proiectjava.demo.model.Team;
import com.proiectjava.demo.repository.StadiumRepository;
import com.proiectjava.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    private final TeamRepository teamRepository;
    public StadiumService(StadiumRepository stadiumRepository, TeamRepository teamRepository) {
        this.stadiumRepository = stadiumRepository;
        this.teamRepository = teamRepository;
    }
    public StadiumDto add(StadiumDto stadiumDto) {
        Stadium stadium = new Stadium();
        stadium.setName(stadiumDto.getName());
        stadium.setCapacity(stadiumDto.getCapacity());
        stadium.setCountry(stadiumDto.getCountry());
        stadiumRepository.save(stadium);
        return stadiumDto;
    }
    public StadiumDto getStadiumById(Integer id) {
        Stadium stadium = stadiumRepository.findById(id).orElse(null);
        if (stadium != null) {
            return StadiumDto.builder()
                    .name(stadium.getName())
                    .capacity(stadium.getCapacity())
                    .country(stadium.getCountry())
                    .build();
        }
        return null;
    }
    public StadiumDto updateStadium(Integer id, StadiumDto stadiumDto) {
        Stadium stadium = stadiumRepository.findById(id).orElse(null);
        if (stadium != null) {
            stadium.setName(stadiumDto.getName());
            stadium.setCountry(stadiumDto.getCountry());
            stadium.setCapacity(stadiumDto.getCapacity());
            stadiumRepository.save(stadium);
            return stadiumDto;
        }
        return null;
    }
    public void deleteStadium(Integer id) {
        // First find teams using this stadium and remove the association
        List<Team> teamsWithStadium = teamRepository.findByStadiumId(id);
        for (Team team : teamsWithStadium) {
            team.setStadium(null);
            teamRepository.save(team);
        }
        // Then delete the stadium
        stadiumRepository.deleteById(id);
    }
}
