package com.example.demo.controllers;

import com.example.demo.UserManager;
import com.example.demo.model.User;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.RoomTypeRepository;
import com.example.demo.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;



@Controller
public class MainController {

    @Autowired
    RoomTypeRepository roomTypeRep;
    @Autowired
    RoomRepository roomRep;

    @GetMapping(path="/")
    public String  getIndex(Map<String,Object> model) {
        model.put("auth", UserManager.isAuth());
        Iterable<RoomType> rooms = roomTypeRep.findAll();
        model.put("rooms",rooms);
        return "index";
    }

    @GetMapping(path="/login")
    public String getLogin(@RequestParam(value = "error",required = false) String error,
                           @RequestParam(value = "logout",required = false) String logout,
                           Map<String,Object> model)
    {
        model.put("auth", UserManager.isAuth());
        if (UserManager.isAuth())
        {
            return "profile";
        } else {
            model.put("error", error != null);
            model.put("logout", logout != null);
            return "login";
        }
    }
}
