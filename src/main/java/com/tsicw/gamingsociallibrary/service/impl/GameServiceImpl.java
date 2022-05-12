package com.tsicw.gamingsociallibrary.service.impl;

import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

  @Autowired private GameRepository gameRepository;

  @Override
  public void addGame(Game game) {
    gameRepository.save(game);
  }
  @Override
  public List<Game> findAllGames() {

    return gameRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    if (gameRepository.existsById(id)) {
      gameRepository.deleteById(id);
    }
  }

  @Override
  public void updateGame(Game game) {
      gameRepository.save(game);
    }

  @Override
  public Optional<Game> findGameById(Long id) {
    return gameRepository.findById(id);
  }

}



