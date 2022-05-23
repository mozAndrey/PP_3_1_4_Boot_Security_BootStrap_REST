package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> getListUsers();

    User findById(Long id);

    void deleteUserById(Long id);

    void updateWithRole(User user, String[] roles);

    User findByUserName(String username);

    void addUserWithRole(User user, String[] roles);
}
