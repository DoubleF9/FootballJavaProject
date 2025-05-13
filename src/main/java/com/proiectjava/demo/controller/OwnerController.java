package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.OwnerDto;
import com.proiectjava.demo.service.OwnerService;
import com.proiectjava.demo.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @PostMapping("/add")
    public OwnerDto add(@RequestBody OwnerDto ownerDto) {
        return ownerService.add(ownerDto);
    }
    @PostMapping("/addOwnerToTeam")
    public OwnerDto addOwnerToTeam(@RequestBody OwnerDto ownerDto, @RequestParam  String teamName) {
        return ownerService.addOwnerToTeam(ownerDto, teamName);
    }
    @PutMapping("/update/{id}")
    public OwnerDto updateOwner(@RequestBody OwnerDto ownerDto, @PathVariable Integer id) {
        return ownerService.updateOwner(ownerDto, id);
    }
}
