package com.example.demo.controllers;

import com.example.demo.UserManager;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repositories.UserOrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserOrderRepository userOrderRepository;

    @GetMapping
    public String getProfile(Map<String, Object> model) {
        model.put("auth", UserManager.isAuth());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        List<UserOrder> userOrderList = userOrderRepository.findAllByUser(user);
        model.put("isAdmin", user.getRole().contains(Role.ADMIN));
        model.put("user", user);
        model.put("orders", userOrderList);
        return "profile";
    }

    @PostMapping("/orders/delete/{userOrder}")
    public String deleteOrder(@PathVariable UserOrder userOrder) {
        userOrderRepository.delete(userOrder);
        return "redirect:/profile";
    }

    @GetMapping("/edit")
    public String getProfileEdit(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.put("auth", UserManager.isAuth());
        model.put("user", user);
        return "profileEdit";
    }

    @PostMapping("/edit")
    public String saveProfile(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String phone,
                              @RequestParam String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        userRepository.save(user);
        return "redirect:/profile";
    }
}

