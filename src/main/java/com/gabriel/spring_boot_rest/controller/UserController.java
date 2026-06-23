package com.gabriel.spring_boot_rest.controller;

import com.gabriel.spring_boot_rest.model.User;
import com.gabriel.spring_boot_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("login")
    public User login(User user) {
        String username = user.getUsername();
        return userService.findByUsername(username);
    }
}
