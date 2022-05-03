package com.tsicw.gamingsociallibrary.business.mappers;


import com.tsicw.gamingsociallibrary.business.repository.domain.Game;
import com.tsicw.gamingsociallibrary.dto.GameDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper{


    GameDTO gametoGameDTO(Game game);

    Game gameDTOToGame(GameDTO GameDTO);



}
