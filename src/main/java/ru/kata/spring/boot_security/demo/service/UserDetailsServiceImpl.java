package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.*;
@Service
public class UserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getListUsers() {

        return userService.getListUsers();
    }

    @Override
    public User findById(Long id) {
        return userService.findById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {

        userService.deleteUserById(id);
    }

    @Override
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
        userService.updateUser(user);
    }

    @Override
    public User findByUserName(String username) {

        return userService.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return userService.findByUserName(username);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void addUserWithRole(User user, String[] role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Arrays.stream(role).forEach(e -> roles.add(roleService.findRole(e)));
        user.setRoles(roles);
        userService.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword().startsWith("$2a$10$") && user.getPassword().length() == 60) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Collections.singleton(roleService.findRole("ROLE_USER")));
        userService.updateUser(user);
    }
}
