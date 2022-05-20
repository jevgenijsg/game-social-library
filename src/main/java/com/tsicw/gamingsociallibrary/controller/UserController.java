package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @GetMapping("/{id}")
    public String showProfile(@PathVariable Long id, Model model){
        Optional<User> loggedInUser = userService.findUserById(id);
        loggedInUser.ifPresent(user -> model.addAttribute("userprofile", user));
        return "user-profile";
    }

    @GetMapping("/{id}/delete-game/{gameId}")
    public String deleteGameFromCollection(@AuthenticationPrincipal User user,@PathVariable("id") Long id, @PathVariable("gameId") Long gameId){
        userService.removeGame(id, gameId);
        return "redirect:/user/{id}";
    }


}
