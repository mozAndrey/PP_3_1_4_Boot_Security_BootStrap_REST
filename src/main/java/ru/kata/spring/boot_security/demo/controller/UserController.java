package ru.kata.spring.boot_security.demo.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/info/{userName}")
    public String info(ModelMap model, @PathVariable String userName) {
        model.addAttribute("userInfo",userService.findById(userName));
        return "user-info2";
    }

    @GetMapping(value = "/update/{userName}")
    public String updateUser(Model model, @PathVariable String userName) {
        model.addAttribute("userInfo",userService.findById(userName));
        return "userUpdate";
    }

    @RequestMapping(value = "/update/u")
    public String updateUser(@ModelAttribute("userInfo") Users users) {
        userService.update(users);
        return "redirect:/user/info/"+users.getUsername();
    }
}
