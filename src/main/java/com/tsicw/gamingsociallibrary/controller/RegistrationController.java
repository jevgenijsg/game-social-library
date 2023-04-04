package com.tsicw.gamingsociallibrary.controller;


import com.tsicw.gamingsociallibrary.repository.domain.Game;
import com.tsicw.gamingsociallibrary.repository.domain.Roles;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
    return "registration";
}

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "registration";
        }else if(userService.loadUserByUsername(user.getUsername()) != null){
            model.addAttribute("message", "Username is already taken");
            return "registration";
        }
        user.setRoles(Collections.singleton(Roles.USER));
        userService.saveUser(user);
    return "redirect:/";
    }
}
