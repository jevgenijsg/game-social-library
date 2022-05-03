package com.tsicw.gamingsociallibrary.business.service;

import com.tsicw.gamingsociallibrary.dto.GameDTO;

import java.util.List;

public interface GameService {

    void addGame(GameDTO GameDTO);

    List<GameDTO> findAllGames();

    void deleteById(Long id);
}
