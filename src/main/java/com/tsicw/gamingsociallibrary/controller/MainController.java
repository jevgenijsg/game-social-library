package com.tsicw.gamingsociallibrary.controller;

import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.GameService;
import com.tsicw.gamingsociallibrary.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            User actualUser = userService.findUserById(user.getId()).get();
            model.addAttribute("user", actualUser);
        }
        model.addAttribute("games", gameService.findAllGames());
        return "main";
    }
}
