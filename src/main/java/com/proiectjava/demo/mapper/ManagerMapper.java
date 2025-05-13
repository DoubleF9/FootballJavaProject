package com.proiectjava.demo.mapper;

import com.proiectjava.demo.dto.ManagerDto;
import com.proiectjava.demo.model.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper extends PersonMapper<Manager, ManagerDto> {
    ManagerDto toDto(Manager manager);

    Manager toEntity(ManagerDto dto);
}