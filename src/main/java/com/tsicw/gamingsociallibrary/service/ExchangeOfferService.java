package com.tsicw.gamingsociallibrary.service;

import com.tsicw.gamingsociallibrary.repository.domain.ExchangeOffer;
import com.tsicw.gamingsociallibrary.repository.domain.User;

import java.util.List;
import java.util.Optional;

public interface ExchangeOfferService {

    public List<ExchangeOffer> getAllOffers();

    void saveExchangeOffer(ExchangeOffer exchangeOffer);

    Optional<ExchangeOffer> findOfferById(Long id);

    void deleteExchangeOffer(Long id);

    void exchangeOffers(User user, Long id);

}
