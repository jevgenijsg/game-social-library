package com.tsicw.gamingsociallibrary.service;



import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;

import java.util.List;
import java.util.Optional;

public interface GameService {

    void addGame(Game GameDTO);

    List<Game> findAllGames();

    void deleteById(Long id);

    void updateGame(Game game);

    Optional<Game> findGameById(Long id);

    boolean gameAlreadyExists(Game game);

    List<Game> findAllExcludeUsers(User user);

}
