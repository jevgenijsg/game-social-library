package com.tsicw.gamingsociallibrary.controller;


import com.tsicw.gamingsociallibrary.repository.domain.ExchangeOffer;
import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.ExchangeOfferService;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/trades")
public class ExchangeOfferController {

    @Autowired
    ExchangeOfferService exchangeOfferService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;


    @GetMapping("/")
    public String showAllExchangeOffers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("trades", exchangeOfferService.getAllOffers());
        model.addAttribute("user", user);
        return "exchanges-main";
    }

    @GetMapping("/show-trade-form")
    public String showExchangeOfferForm(@AuthenticationPrincipal User user, Model model){

        User actualUser = userService.findUserById(user.getId()).get();

        model.addAttribute("games", gameService.findAllExcludeUsers(actualUser));
        model.addAttribute("usergames", actualUser.getCollection());
        model.addAttribute("trade", new ExchangeOffer());
        return "add-trade-form";
    }

    @PostMapping("/add-trade")
    public String addExchangeOffer(@AuthenticationPrincipal User user,
                                   @Valid ExchangeOffer exchangeOffer, BindingResult bindingResult,
                                   @RequestParam(name = "usergamechoise") Long usergame,
                                   @RequestParam(name = "dbgame") Long game){
        if(bindingResult.hasErrors()){
            return "add-trade-form";
        }

        Optional<Game> dbgame = gameService.findGameById(game);
        Optional<Game> userGame = gameService.findGameById(usergame);

        exchangeOffer.setExchangeGame(dbgame.get());
        exchangeOffer.setGame(userGame.get());
        exchangeOffer.setActive(true);
        exchangeOffer.setUser(user);
        exchangeOfferService.saveExchangeOffer(exchangeOffer);
        return "redirect:/trades/";
    }

    @GetMapping("/trade/{id}")
    public String acceptTrade(@AuthenticationPrincipal User user,
                              @PathVariable("id") Long offerId) {

        if(user.getCollection().contains(exchangeOfferService.findOfferById(offerId).get().getExchangeGame())){
            User buyer = userService.findUserById(user.getId()).get();
            exchangeOfferService.exchangeOffers(buyer, offerId);
        }
/*        userService.updateUserData(user);
        userService.updateUserData(exchangeOfferService.findOfferById(offerId).get().getUser());*/

        return "redirect:/trades/";
    }




}
