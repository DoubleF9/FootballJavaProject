package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.RefereeDto;
import com.proiectjava.demo.model.Referee;
import com.proiectjava.demo.service.RefereeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/referee")

public class RefereeController {
    private final RefereeService refereeService;

    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }
    @GetMapping("/all")
    public List<Referee> getReferees() {
        return refereeService.getReferees();
    }
    @PostMapping("/add")
    public RefereeDto add(@RequestBody RefereeDto refereeDto) {
        return refereeService.add(refereeDto);
    }
}
