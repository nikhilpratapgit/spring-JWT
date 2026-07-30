package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuth {
    private final AuthService service;

    public UserAuth(AuthService service) {
        this.service = service;
    }
    @PostMapping("/signup")
    public User signUp(@RequestBody User user){
        return service.signUp(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return service.login(user);
    }

}
