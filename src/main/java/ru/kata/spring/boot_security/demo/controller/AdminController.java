package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String startAdmin(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping(value = "/remove")
    public String removeUser(@RequestParam Integer id) {
        userService.remove(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles) {
        List<String> roleNames = Arrays.asList(selectedRoles);
        List<Role> roles = roleService.findByNameIn(roleNames);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "addUser";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles) {
        List<String> roleNames = Arrays.asList(selectedRoles);
        List<Role> roles = roleService.findByNameIn(roleNames);
        user.setRoles(roles);
        user.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
        userService.create(user);
        return "redirect:/admin";
    }

}