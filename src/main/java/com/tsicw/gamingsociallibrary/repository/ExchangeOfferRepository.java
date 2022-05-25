package com.tsicw.gamingsociallibrary.repository;

import com.tsicw.gamingsociallibrary.repository.domain.ExchangeOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeOfferRepository extends JpaRepository<ExchangeOffer, Long> {
}
