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
    public List<RefereeDto> getReferees() {
        return refereeService.getReferees();
    }
    @PostMapping("/add")
    public RefereeDto add(@RequestBody RefereeDto refereeDto) {
        return refereeService.add(refereeDto);
    }
    @GetMapping("getReferee/{id}")
    public RefereeDto getRefereeById(@PathVariable Integer id) {
        return refereeService.getRefereeById(id);
    }
    @PutMapping("/update/{id}")
    public RefereeDto updateReferee(@PathVariable Integer id, @RequestBody RefereeDto refereeDto) {
        return refereeService.updateReferee(id, refereeDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteReferee(@PathVariable Integer id) {
        refereeService.deleteReferee(id);
    }

}
