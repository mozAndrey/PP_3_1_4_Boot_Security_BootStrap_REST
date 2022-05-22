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
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getListUsers() {
        return userService.getListUsers();
    }

    public User findById(Long id) {
        return userService.findById(id);
    }

    @Transactional
    public void deleteUserById(Long id) {
        userService.deleteUserById(id);
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
        userService.updateUser(user);
    }

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

    @Transactional
    public void addUserWithRole(User user, String[] role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Arrays.stream(role).forEach(e -> roles.add(roleService.findRole(e)));
        user.setRoles(roles);
        userService.addUser(user);
    }
}
