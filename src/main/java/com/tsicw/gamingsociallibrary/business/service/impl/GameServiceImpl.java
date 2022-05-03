package com.tsicw.gamingsociallibrary.business.service.impl;

import com.tsicw.gamingsociallibrary.business.mappers.GameMapper;
import com.tsicw.gamingsociallibrary.business.repository.domain.Game;
import com.tsicw.gamingsociallibrary.business.service.GameService;
import com.tsicw.gamingsociallibrary.dto.GameDTO;
import com.tsicw.gamingsociallibrary.business.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

  @Autowired private GameRepository gameRepository;
  @Autowired private GameMapper mapper;

  public GameServiceImpl() {}

  @Override
  public void addGame(GameDTO GameDTO) {
    gameRepository.save(mapper.gameDTOToGame(GameDTO));
  }
  @Override
  public List<GameDTO> findAllGames() {
    List<Game> gamesDAO = gameRepository.findAll();
    List<GameDTO> GameDTOS = new ArrayList<>();
    for (Game game : gamesDAO) {
      GameDTOS.add(mapper.gametoGameDTO(game));
    }
    return GameDTOS;
  }

  @Override
  public void deleteById(Long id) {
    if (gameRepository.existsById(id)) {
      gameRepository.deleteById(id);
    }
  }
}
