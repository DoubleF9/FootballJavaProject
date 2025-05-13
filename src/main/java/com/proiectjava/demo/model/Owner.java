package com.proiectjava.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "owner")
@NoArgsConstructor
public class Owner extends Person {


    @Column(name = "NET_WORTH")
    private Integer netWorth;



}