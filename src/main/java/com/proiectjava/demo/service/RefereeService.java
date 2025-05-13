package com.proiectjava.demo.service;

import com.proiectjava.demo.dto.RefereeDto;
import com.proiectjava.demo.model.Referee;
import com.proiectjava.demo.repository.RefereeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefereeService {
    @Autowired
    private RefereeRepository refereeRepository;
     public List<Referee> getReferees() {
         return refereeRepository.findAll();
     }
     public RefereeDto add(RefereeDto refereeDto) {
            Referee referee = new Referee();
            referee.setFirstName(refereeDto.getFirstName());
            referee.setLastName(refereeDto.getLastName());
            referee.setCountry(refereeDto.getCountry());
            referee.setDateOfBirth(refereeDto.getDateOfBirth());
            referee.setSalary(refereeDto.getSalary());
            refereeRepository.save(referee);
            return refereeDto;
     }
}
