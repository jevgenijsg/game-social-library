package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.repository.domain.Genre;
import com.tsicw.gamingsociallibrary.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/games")
@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    Set<Genre> genres = EnumSet.allOf(Genre.class);

    @GetMapping("/show-add-form")
    public String addGameForm(Model model){
        model.addAttribute("genres", genres);
        model.addAttribute("game", new Game());
        return "add-game";
    }

    @PostMapping("/add-game")
    public String addGame(@AuthenticationPrincipal User user,
            @Valid Game game, BindingResult bindingResult, Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        if(bindingResult.hasErrors() || gameService.gameAlreadyExists(game)){
            model.addAttribute("genres", genres);
            model.addAttribute("message", "Game Already exists!");
            return "add-game";
    }
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDirectory = new File(uploadPath);
            if(!uploadDirectory.exists()){
                uploadDirectory.mkdir();
            }
            String fileName = file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/"+ fileName));
            game.setFilename(fileName);
        } else{

            game.setFilename("default-logo.png");

        }
        game.addUser(user);
        gameService.addGame(game);
        return "redirect:/";
    }

    @GetMapping("/show-update-form/{id}")
    public String showUpdateGameForm(@PathVariable("id") Long id, Model model){
        if(gameService.findGameById(id).isPresent()) {
            Game game = gameService.findGameById(id).get();
            model.addAttribute("game", game);
            model.addAttribute("genres", genres);
        }
        return "redirect:/";
    }

    @PostMapping("/update-game/{id}")
    public String updateGame(@PathVariable("id") Long id,
                             @Valid Game game, BindingResult bindingResult){
        gameService.updateGame(game);
        return "redirect:/";
  }

    @GetMapping("/add-to-collection/{id}")
    public String addGameToCollection(@AuthenticationPrincipal User user, @PathVariable("id") Long id){
        if(gameService.findGameById(id).isPresent()){
            Game game = gameService.findGameById(id).get();
            game.addUser(user);
            gameService.updateGame(game);
        }
        return "redirect:/";
    }
}
