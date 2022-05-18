package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.CustomUserDetailsService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final CustomUserDetailsService userService;

    @Autowired
    public UserController(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/info/{userName}")
    public String info(ModelMap model, @PathVariable String userName) {
        model.addAttribute("userInfo", userService.findByUserName(userName));
        return "user-info2";
    }

    @GetMapping(value = "/update/{userName}")
    public String updateUser(Model model, @PathVariable String userName) {
        model.addAttribute("userInfo", userService.findByUserName(userName));
        return "userUpdate";
    }

    @PatchMapping(value = "/update/u")
    public String updateUser(@ModelAttribute("userInfo") User user) {
        userService.updateUser(user);
        return "redirect:/user/info/" + user.getUsername();
    }
}
