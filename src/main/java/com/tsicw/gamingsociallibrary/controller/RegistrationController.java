package com.tsicw.gamingsociallibrary.controller;


import com.tsicw.gamingsociallibrary.repository.domain.Roles;
import com.tsicw.gamingsociallibrary.repository.domain.User;
import com.tsicw.gamingsociallibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(){
    return "registration";
}

    @PostMapping("/registration")
    public String addUser(User user, Model model){

        User userFromDB = userService.findUserByName(user.getUsername());
        if(userFromDB != null){
            model.addAttribute("message", "User already exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Roles.USER));
        userService.save(user);

    return "redirect:/login";
}

}
