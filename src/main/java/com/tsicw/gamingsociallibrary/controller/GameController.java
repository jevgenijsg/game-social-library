package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.repository.domain.Genre;
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
import java.util.Set;
import java.util.UUID;

@RequestMapping("/games")
@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    @Value("${upload.path}")
    private String uploadPath;

    Set<Genre> genres = EnumSet.allOf(Genre.class);

    @GetMapping("/show-add-form")
    public String addGameForm(Model model){
        model.addAttribute("genres", genres);
        model.addAttribute("game", new Game());
        return "add-game";
    }

    @PostMapping(value = "/add-game")
    public String addGame(
            @AuthenticationPrincipal User user,
            @Valid Game game, BindingResult bindingResult, Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            model.addAttribute("genres", genres);
            return "add-game";
    }
        if(file != null){
            File uploadDirectory = new File(uploadPath);
            if(uploadDirectory.exists()){
                uploadDirectory.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultName = uuidFile + "." + file.getOriginalFilename();
            game.setFilename(resultName);
            file.transferTo(new File(uploadPath + "/"+ resultName));
        }
      gameService.addGame(game);
        return "redirect:/";
    }

    @GetMapping("/show-update-form/{id}")
    public String showUpdateGameForm(@PathVariable("id") Long id, Model model){
        if(gameService.findGameById(id).isPresent()) {
            Game game = gameService.findGameById(id).get();
            model.addAttribute("game", game);
            model.addAttribute("genres", genres);
            return "update-form";
        }
        return "redirect:/";
    }

    @PostMapping("/update-game/{id}")
    public String updateGame(@PathVariable("id") Long id,
                             @Valid Game game, BindingResult bindingResult){
        gameService.updateGame(game);
        return "redirect:/";
  }

    @GetMapping("/delete-game/{id}")
    public String deleteGame(@PathVariable("id") Long id){
        gameService.deleteById(id);
        return "redirect:/";
    }
}
