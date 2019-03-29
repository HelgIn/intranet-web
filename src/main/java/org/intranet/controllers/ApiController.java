package org.intranet.controllers;

import org.intranet.entity.News;
import org.intranet.entity.User;
import org.intranet.services.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    private final DaoService daoService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApiController(DaoService daoService, PasswordEncoder passwordEncoder) {
        this.daoService = daoService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/news")
    public Iterable<News> getNews() {
        return daoService.getNews();
    }

    @PostMapping(value = "/news/add")
    public News addNews(@RequestBody News news) {
        return daoService.saveNews(news);
    }

    @PostMapping(value = "/register")
    public void addUser(@RequestBody User user) {
        // TODO temp
        daoService.saveUser(user);
    }

    @GetMapping(value = "/users")
    public Iterable<User> getAllUsers() {
        return daoService.findAllUsers();
    }

    @GetMapping("/login")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping(value = "/user")
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = new User();
        user.setUsername(currentPrincipalName);
        return user;
    }

    @GetMapping(value = "/ping")
    public String ping() {
        return "ok";
    }
}
