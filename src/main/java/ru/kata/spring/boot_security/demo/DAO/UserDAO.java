package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.security.core.userdetails.User;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;
public interface UserDAO {

    List<Users> getListOfUsers();

    void addUser(Users users);

    Users findById(String id);

    void deleteById(String username);

    void update(Users users);
}
