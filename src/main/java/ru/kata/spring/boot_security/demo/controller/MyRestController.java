package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class MyRestController {

    private final UserService userService;

    @Autowired
    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/api/listOfUsers")
    public List<User> listOfUsers() {
        return userService.getListUsers();
    }

    @PostMapping("admin/api/save")
    public List<User> saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return userService.getListUsers();
    }

    @DeleteMapping("admin/api/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("admin/api/update")
    public void updateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    @GetMapping("/user/api")
    public User getUser(Principal principal) {
        return userService.findByUserName(principal.getName());
    }
}
