package org.intranet.controllers;

import org.intranet.entity.News;
import org.intranet.entity.User;
import org.intranet.services.DaoService;
import org.intranet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    private final DaoService daoService;

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    private final UserService userService;

    @Autowired
    public ApiController(DaoService daoService, JdbcUserDetailsManager jdbcUserDetailsManager, UserService userService) {
        this.daoService = daoService;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.userService = userService;
    }

    @GetMapping(value = "/news")
    public Iterable<News> getNews() {
        return daoService.getNews();
    }

    @GetMapping(value = "/news/{id}/")
    public News getNewsById(@PathVariable("id") long id) throws InterruptedException {
        Thread.sleep(2000);
        return daoService.getNewsById(id);
    }

    @PostMapping(value = "/news/add")
    public News addNews(@RequestBody News news) {
        return daoService.saveNews(news);
    }

    @PostMapping(value = "/register")
    public void addUser(@RequestBody User user) {
        jdbcUserDetailsManager.createUser(userService.mapUserToUserDetails(user));
    }

    @GetMapping(value = "/users")
    public Iterable<User> getAllUsers() {
        return daoService.findAllUsers();
    }

    @GetMapping("/login")
    public Principal login(Principal user) {
        return user;
    }

    @GetMapping(value = "/user")
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return daoService.findByUsername(currentPrincipalName);
    }

    @PostMapping(value = "/user/update")
    public void getUser(@RequestBody User user) {
        daoService.updateUser(user);
    }

    @GetMapping(value = "/ping")
    public String ping() {
        return "ok";
    }
}
