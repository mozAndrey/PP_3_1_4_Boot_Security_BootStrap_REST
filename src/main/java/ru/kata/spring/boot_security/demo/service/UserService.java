package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> getListUsers();

    void addUser(User user);

    User findById(Long id);

    void deleteUserById(Long id);

    void update(User user);

    User findByUserName(String username);
}
