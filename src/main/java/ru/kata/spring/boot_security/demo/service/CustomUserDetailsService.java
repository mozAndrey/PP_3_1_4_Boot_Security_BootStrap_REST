package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface CustomUserDetailsService {
    List<User> getListUsers();

    User findById(Long id);

    void deleteUserById(Long id);

    void updateWithRole(User user, String[] role);

    User findByUserName(String username);

    void addUserWithRole(User user, String[] role);

    void updateUser(User user);
}
