package com.proiectjava.demo.controller;

import com.proiectjava.demo.dto.ManagerDto;
import com.proiectjava.demo.service.ManagerService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    @PostMapping("/add")
    public ManagerDto addManager(@RequestBody ManagerDto managerDto, Integer teamId) {
        return managerService.addManager(managerDto, teamId);
    }
    @PutMapping("changeManager/{teamId}")
    public ManagerDto changeManager(@RequestBody ManagerDto managerDto,@RequestParam Integer teamId) {
        return managerService.changeManager(managerDto, teamId);
    }
    @PostMapping("/addManagerWithoutTeam")
    public ManagerDto addManagerWithoutTeam(@RequestBody ManagerDto managerDto) {
        return managerService.addManagerWithoutTeam(managerDto);
    }
}
