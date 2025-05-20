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

     public RefereeDto add(RefereeDto refereeDto) {
            Referee referee = new Referee();
            referee.setFirstName(refereeDto.getFirstName());
            referee.setLastName(refereeDto.getLastName());
            referee.setCountry(refereeDto.getCountry());
            referee.setDateOfBirth(refereeDto.getDateOfBirth());
            referee.setSalary(refereeDto.getSalary());
            referee.setIsFifaRegistered(refereeDto.getIsFifaRegistered());
            refereeRepository.save(referee);
            return refereeDto;
     }
    public List<RefereeDto> getReferees() {
        return refereeRepository.findAll()
                .stream()
                .map(referee -> new RefereeDto(referee.getFirstName(), referee.getLastName(), referee.getDateOfBirth(), referee.getCountry(), referee.getSalary(), referee.getIsFifaRegistered()))
                .toList();
    }
    public RefereeDto getRefereeById(Integer id) {
        Referee referee = refereeRepository.findById(id).orElse(null);
        if (referee != null) {
            return new RefereeDto(referee.getFirstName(), referee.getLastName(), referee.getDateOfBirth(), referee.getCountry(), referee.getSalary(), referee.getIsFifaRegistered());
        }
        return null;
    }

    public RefereeDto updateReferee(Integer id, RefereeDto refereeDto) {
        Referee referee = refereeRepository.findById(id).orElse(null);
        if (referee != null) {
            referee.setFirstName(refereeDto.getFirstName());
            referee.setLastName(refereeDto.getLastName());
            referee.setCountry(refereeDto.getCountry());
            referee.setDateOfBirth(refereeDto.getDateOfBirth());
            referee.setSalary(refereeDto.getSalary());
            referee.setIsFifaRegistered(refereeDto.getIsFifaRegistered());
            refereeRepository.save(referee);
            return refereeDto;
        }
        return null;
    }
    public void deleteReferee(Integer id) {
        refereeRepository.deleteById(id);
    }
}
