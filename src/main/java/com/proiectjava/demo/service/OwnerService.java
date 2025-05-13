package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.OwnerDto;
import com.proiectjava.demo.model.Owner;
import com.proiectjava.demo.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    public OwnerDto add(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setFirstName(ownerDto.getFirstName());
        owner.setLastName(ownerDto.getLastName());
        owner.setCountry(ownerDto.getCountry());
        owner.setDateOfBirth(ownerDto.getDateOfBirth());
        owner.setNetWorth(ownerDto.getNetWorth());
        ownerRepository.save(owner);
        return ownerDto;
    }

}
