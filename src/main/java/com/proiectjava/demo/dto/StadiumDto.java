package com.proiectjava.demo.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StadiumDto {

    private String name;
    private String country;
    private Integer capacity;

}