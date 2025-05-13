package com.proiectjava.demo.mapper;

import com.proiectjava.demo.dto.StadiumDto;
import com.proiectjava.demo.model.Stadium;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StadiumMapper {
    StadiumDto toDto(Stadium stadium);
    Stadium toEntity(StadiumDto dto);
}