package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.OwnerDto;
import com.proiectjava.demo.service.OwnerService;
import com.proiectjava.demo.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
