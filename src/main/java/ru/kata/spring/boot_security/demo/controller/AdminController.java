package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.DAO.UserDaoImpl;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/listOfUsers")
    public String listOfUsers(Model modelMap) {
        modelMap.addAttribute("listOfUsers", userService.getListUsers());
        return "users";
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(@ModelAttribute("user") Users users) {
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") Users users) {
        userService.addUser(users);
        return "redirect:/admin/listOfUsers";
    }

    @GetMapping(value = "/update")
    public String updateUser(Model model, @RequestParam("idToUpdate") String username) {
        model.addAttribute("userToUpdate",userService.findById(username));
        return "update";
    }

    @PatchMapping(value = "/update/u")
    public String update(@ModelAttribute("userToUpdate") Users users) {
        userService.update(users);
        return "redirect:/admin/listOfUsers";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam("idToDelete") String username) {
        userService.deleteUserById(username);
        return "redirect:/admin/listOfUsers";
    }


}
