package com.tsicw.gamingsociallibrary.service.impl;

import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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

  @Override
  public boolean gameAlreadyExists(Game game) {
    return gameRepository.exists(Example.of(game));
  }

  public List<Game> findAllExcludeUsers(User user){
    List<Game> allgames = gameRepository.findAll();
     allgames.removeAll(user.getCollection());

     return allgames;
  }
}