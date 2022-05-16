package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.User;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;

public interface UserService {

    List<Users> getListUsers();

    void addUser(Users users);

    Users findById(String username);

    void deleteUserById(String username);

    void update(Users users);
}
