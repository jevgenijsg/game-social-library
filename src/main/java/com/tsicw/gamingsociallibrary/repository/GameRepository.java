package com.tsicw.gamingsociallibrary.repository;

import com.tsicw.gamingsociallibrary.repository.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
