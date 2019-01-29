package com.example.demo.controllers;

import com.example.demo.UserManager;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/registration")
    public String registration() {
        if (UserManager.isAuth()) {
            return "profile";
        } else {
            return "registration";
        }
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUserLogin(user.getUserLogin());

        if (userFromDb != null) { //check user existing
            model.put("message", "User exists!");
            return "registration";
        }
        user.setRole(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
