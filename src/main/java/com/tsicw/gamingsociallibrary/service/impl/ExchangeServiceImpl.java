package com.tsicw.gamingsociallibrary.service.impl;

import com.tsicw.gamingsociallibrary.repository.ExchangeOfferRepository;
import com.tsicw.gamingsociallibrary.repository.domain.ExchangeOffer;
import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.ExchangeOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeOfferService {

    @Autowired
    ExchangeOfferRepository exchangeOfferRepository;

    @Autowired
    UserService userService;

    public List<ExchangeOffer> getAllOffers(){
        return exchangeOfferRepository.findAll();
    }

    @Override
    public void saveExchangeOffer(ExchangeOffer exchangeOffer) {
        exchangeOfferRepository.save(exchangeOffer);
    }

    @Override
    public Optional<ExchangeOffer> findOfferById(Long id) {
        return exchangeOfferRepository.findById(id);
    }

    @Override
    public void deleteExchangeOffer(Long id) {
        exchangeOfferRepository.deleteById(id);
    }

    @Override
    public void exchangeOffers(User user, Long id) {

        ExchangeOffer offerToProcess = exchangeOfferRepository.getById(id);

        user.getCollection().add(offerToProcess.getGame());
        user.getCollection().remove(offerToProcess.getExchangeGame());

        offerToProcess.getUser().getCollection().add(offerToProcess.getExchangeGame());
        offerToProcess.getUser().getCollection().remove(offerToProcess.getGame());

        userService.updateUserData(user);
        userService.updateUserData(offerToProcess.getUser());
        exchangeOfferRepository.deleteById(id);
    }


}
