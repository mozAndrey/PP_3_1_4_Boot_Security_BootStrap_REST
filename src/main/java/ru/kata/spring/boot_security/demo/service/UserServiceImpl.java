package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getListUsers() {
        return userDAO.getListOfUsers();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteById(id);
    }

    @Transactional
    public void updateWithRole(User user, String[] role) {
        if (user.getPassword().startsWith("$2a$10$") && user.getPassword().length() == 60) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();
        Arrays.stream(role).forEach(e -> roles.add(roleService.findRole(e)));
        user.setRoles(roles);
        userDAO.update(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Transactional
    public void addUserWithRole(User user, String[] role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Arrays.stream(role).forEach(e -> roles.add(roleService.findRole(e)));
        user.setRoles(roles);
        userDAO.addUser(user);
    }
}
