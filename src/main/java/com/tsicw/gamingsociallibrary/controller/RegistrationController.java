package com.tsicw.gamingsociallibrary.controller;


import com.tsicw.gamingsociallibrary.repository.domain.Roles;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.impl.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {


    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(){
    return "registration";
}

    @PostMapping("/registration")
    public String addUser(User user, Model model){

        UserDetails userFromDB = userService.loadUserByUsername(user.getUsername());
        if(userFromDB != null){
            model.addAttribute("message", "User already exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Roles.USER));
        userService.saveUser(user);

    return "redirect:/login";
}

}
