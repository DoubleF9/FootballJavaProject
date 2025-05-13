package com.proiectjava.demo.mapper;

import com.proiectjava.demo.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

public interface PersonMapper<E extends Person, D> {
    // Defining only default methods with concrete implementations
    default E updateEntityFromDto(D dto, @MappingTarget E entity) {
        return entity; // To be overridden by implementing classes
    }
}