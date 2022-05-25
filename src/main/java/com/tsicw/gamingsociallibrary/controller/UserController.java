package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.repository.domain.User;
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

import javax.validation.Valid;
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
    public String deleteGameFromCollection(@PathVariable("id") Long userId, @PathVariable("gameId") Long gameId){
        userService.removeGame(userId, gameId);
        return "redirect:/user/{id}";
    }

    @GetMapping("/{id}/show-update-profile")
    public String showUpdateProfile(@PathVariable("id") Long id, Model model){
        if(userService.findUserById(id).isPresent()) {
            User user = userService.findUserById(id).get();
            model.addAttribute("user", user);
            return "update-profile";
        }
        return "redirect:/user/{id}";
    }

    @PostMapping("/{id}/update-profile")
    public String updateProfile(@PathVariable("id") Long id, @Valid User user,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "update-profile";
        }
        userService.updateUserData(user);
        return "redirect:/user/{id}";
    }
}
