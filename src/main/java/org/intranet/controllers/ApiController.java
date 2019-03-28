package org.intranet.controllers;

import org.intranet.entity.User;
import org.intranet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping(value = "/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/login")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping(value = "/ping")
    public String ping() {
        return "ok";
    }
}
