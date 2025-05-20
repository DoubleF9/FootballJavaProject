package com.proiectjava.demo.mapper;

import com.proiectjava.demo.dto.TeamDto;
import com.proiectjava.demo.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {LeagueMapper.class, StadiumMapper.class, ManagerMapper.class, OwnerMapper.class})
public interface TeamMapper {
    @Mapping(target = "league", ignore = true)
    TeamDto toDto(Team team);
    Team toEntity(TeamDto dto);
}