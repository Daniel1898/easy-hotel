package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    public static boolean isAuth()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !auth.getPrincipal().equals("anonymousUser");
    }
}
