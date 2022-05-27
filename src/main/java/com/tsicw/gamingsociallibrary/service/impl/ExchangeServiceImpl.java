package com.tsicw.gamingsociallibrary.service.impl;

import com.tsicw.gamingsociallibrary.repository.ExchangeOfferRepository;
import com.tsicw.gamingsociallibrary.repository.domain.ExchangeOffer;
import com.tsicw.gamingsociallibrary.service.ExchangeOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeOfferService {

    @Autowired
    ExchangeOfferRepository exchangeOfferRepository;

    public List<ExchangeOffer> getAllOffers(){
        return exchangeOfferRepository.findAll();
    }

    @Override
    public void saveExchangeOffer(ExchangeOffer exchangeOffer) {
        exchangeOfferRepository.save(exchangeOffer);
    }
}
