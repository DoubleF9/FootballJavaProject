package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.StadiumDto;
import com.proiectjava.demo.model.Stadium;
import com.proiectjava.demo.repository.StadiumRepository;
import org.springframework.stereotype.Service;

@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }
    public Stadium add(StadiumDto stadiumDto) {
        Stadium stadium = new Stadium();
        stadium.setName(stadiumDto.getName());
        stadium.setCapacity(stadiumDto.getCapacity());
        return stadiumRepository.save(stadium);
    }
}
