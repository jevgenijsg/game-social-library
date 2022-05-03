package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.business.service.GameService;
import com.tsicw.gamingsociallibrary.business.repository.domain.Genre;
import com.tsicw.gamingsociallibrary.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    Set<Genre> genres = EnumSet.allOf(Genre.class);

    @GetMapping("/welcome")
    public String greeting(Model model) {
       List<GameDTO> GameDTOS = gameService.findAllGames();
        model.addAttribute("games", GameDTOS);
        return "welcome";
    }

    @GetMapping("/add")
    public String addGameForm(Model model){
        model.addAttribute("genres", genres);
        model.addAttribute("game", new GameDTO());
        return "add-game";
    }

    @PostMapping(value = "/process-add")
    public String addGame(@Valid @ModelAttribute("game") GameDTO gameDTO,  BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("genres", genres);
            return "add-game";
    }
      gameService.addGame(gameDTO);
        return "redirect:/welcome";
    }

    @GetMapping("/delete-game")
    public String deleteGame(@RequestParam Long id){
        gameService.deleteById(id);
        return "redirect:/welcome";
    }
}
