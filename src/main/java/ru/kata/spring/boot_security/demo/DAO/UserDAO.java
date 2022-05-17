package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
public interface UserDAO {

    List<User> getListOfUsers();

    void addUser(User user);

    User findById(Long id);

    void deleteById(Long id);

    void update(User user);

    User findByUserName(String username);
}
