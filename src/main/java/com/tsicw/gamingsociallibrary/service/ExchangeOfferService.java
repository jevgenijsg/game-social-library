package com.tsicw.gamingsociallibrary.service;

import com.tsicw.gamingsociallibrary.repository.domain.ExchangeOffer;

import java.util.List;

public interface ExchangeOfferService {

    public List<ExchangeOffer> getAllOffers();

    void saveExchangeOffer(ExchangeOffer exchangeOffer);

}
