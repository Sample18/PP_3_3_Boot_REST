package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/remove")
    public List<User> removeUser(@RequestParam Integer id) {
        userService.remove(id);
        return userService.getAllUsers();
    }

    @PostMapping(value = "/edit")
    public List<User> updateUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles) {
        userService.update(user, selectedRoles);
        return userService.getAllUsers();
    }

    @PostMapping(value = "/new")
    public List<User> createUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles) {
        userService.create(user, selectedRoles);
        return userService.getAllUsers();
    }

}