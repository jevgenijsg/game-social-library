package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    GameServiceImpl gameService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("games", gameService.findAllGames());
        return "main";
    }
}
