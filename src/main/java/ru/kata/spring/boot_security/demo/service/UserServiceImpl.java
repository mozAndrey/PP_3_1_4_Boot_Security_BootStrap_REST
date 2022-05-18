package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAOImpl;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserDAO userDAO;
    private RoleDAOImpl roleDAOImpl;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setRoleDAO(RoleDAOImpl roleDAOImpl) {
        this.roleDAOImpl = roleDAOImpl;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getListUsers() {
        return userDAO.getListOfUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void updateWithRole(User user, String role) {
        if (user.getPassword().startsWith("$2a$10$") && user.getPassword().length() == 60) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Collections.singleton(roleDAOImpl.findRole(role)));
        userDAO.update(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return userDAO.findByUserName(username);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void addUserWithRole(User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleDAOImpl.findRole(role)));
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword().startsWith("$2a$10$") && user.getPassword().length() == 60) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Collections.singleton(roleDAOImpl.findRole("ROLE_USER")));
        userDAO.update(user);
    }
}
