package com.proiectjava.demo.mapper;

import com.proiectjava.demo.dto.PlayerDto;
import com.proiectjava.demo.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PlayerMapper extends PersonMapper<Player, PlayerDto> {

    PlayerDto toDto(Player player);

    Player toEntity(PlayerDto dto);
}
