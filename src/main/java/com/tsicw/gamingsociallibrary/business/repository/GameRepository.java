package com.tsicw.gamingsociallibrary.business.repository;

import com.tsicw.gamingsociallibrary.business.repository.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
