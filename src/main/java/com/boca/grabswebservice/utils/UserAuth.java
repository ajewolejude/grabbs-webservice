package com.boca.grabswebservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuth {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String role = authentication.getAuthorities().toString();
    String email = authentication.getName();


    public Authentication getAuthentication() {
        return authentication;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}
