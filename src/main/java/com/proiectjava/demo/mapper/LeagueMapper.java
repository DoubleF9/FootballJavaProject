package com.proiectjava.demo.mapper;

import com.proiectjava.demo.dto.LeagueDto;
import com.proiectjava.demo.model.League;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeagueMapper {
    LeagueDto toDto(League league);
    League toEntity(LeagueDto dto);
}