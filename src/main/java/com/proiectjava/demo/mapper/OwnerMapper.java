package com.proiectjava.demo.mapper;

import com.proiectjava.demo.dto.OwnerDto;
import com.proiectjava.demo.model.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends PersonMapper<Owner, OwnerDto> {
    OwnerDto toDto(Owner owner);
    Owner toEntity(OwnerDto dto);
}