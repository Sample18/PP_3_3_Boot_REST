package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void remove(int id);

    void create(User user);

    void update(User user);

    User findById(int id);

    User findByUsername(String username);
}
