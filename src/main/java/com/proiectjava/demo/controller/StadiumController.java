package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.StadiumDto;
import com.proiectjava.demo.service.StadiumService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stadium")
public class StadiumController {
    private final StadiumService stadiumService;
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }
    @PostMapping("/add")
    public StadiumDto addStadium(StadiumDto stadiumDto) {
        return stadiumService.add(stadiumDto);
    }
    @PostMapping("/get")
    public StadiumDto getStadiumById(Integer id) {
        return stadiumService.getStadiumById(id);
    }
    @PutMapping("/update")
    public StadiumDto updateStadium(Integer id, StadiumDto stadiumDto) {
        return stadiumService.updateStadium(id, stadiumDto);
    }
    @PostMapping("/delete")
    public void deleteStadium(Integer id) {
        stadiumService.deleteStadium(id);
    }
}
