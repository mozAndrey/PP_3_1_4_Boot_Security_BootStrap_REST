package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> getListUsers();

    void deleteUserById(Long id);

    User findByUserName(String username);

    void saveOrUpdate(User user);
}
