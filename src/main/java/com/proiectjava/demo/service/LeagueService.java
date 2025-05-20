package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.LeagueDto;
import com.proiectjava.demo.dto.TeamDto;
import com.proiectjava.demo.mapper.LeagueMapper;
import com.proiectjava.demo.mapper.TeamMapper;
import com.proiectjava.demo.model.League;
import com.proiectjava.demo.model.Team;
import com.proiectjava.demo.repository.LeagueRepository;
import com.proiectjava.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LeagueService {
    private final TeamRepository teamRepository;
    private final LeagueMapper leagueMapper;
    private final TeamMapper teamMapper;

    public LeagueService( TeamRepository teamRepository, LeagueMapper leagueMapper, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.leagueMapper = leagueMapper;
        this.teamMapper = teamMapper;
    }

    public String printAllLeaguesWithTeams(HashMap<LeagueDto, List<TeamDto>> leagues) {
        String result = "";
        for (LeagueDto leagueDto : leagues.keySet()) {
            result += "League: " + leagueDto.getName() + "\n";
            result += "Teams:\n";
            for (TeamDto team : leagues.get(leagueDto)) {
                result += "- " + team.getName() + "\n";
            }
            result += "\n";
        }
        return result;
    }

    public HashMap<LeagueDto, List<TeamDto>> allLeaguesWithTeams() {
        HashMap<LeagueDto, List<TeamDto>> leagueTeamMap = new HashMap<>();

        for (Team team : teamRepository.findAll()) {
            if (team.getLeague() != null) {
                League league = team.getLeague();
                LeagueDto leagueDto = leagueMapper.toDto(league);

                if (!leagueTeamMap.containsKey(leagueDto)) {
                    leagueTeamMap.put(leagueDto, new ArrayList<>());
                }

                // Convert Team to TeamDto - only include necessary properties
                TeamDto teamDto = teamMapper.toDto(team);
                // Add other needed properties, but avoid including references to entities

                leagueTeamMap.get(leagueDto).add(teamDto);
            }
        }
        return leagueTeamMap;
    }
}
