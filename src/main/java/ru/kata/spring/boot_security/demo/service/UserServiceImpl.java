package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getListUsers() {
        return userDAO.getListOfUsers();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public void updateWithRole(User user) {
        userDAO.update(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }
}
