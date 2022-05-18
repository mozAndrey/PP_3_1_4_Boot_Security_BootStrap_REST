package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
@Secured(value = {"ROLE_ADMIN"})
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
    public String addNewUser(@ModelAttribute("user") User user) {
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role") String role) {
        userService.addUserWithRole(user, role);
        return "redirect:/admin/listOfUsers";
    }

    @GetMapping(value = "/update")
    public String updateUser(Model model, @RequestParam("idToUpdate") Long id) {
        model.addAttribute("userToUpdate", userService.findById(id));
        return "update";
    }

    @PatchMapping(value = "/update/u")
    public String update(@ModelAttribute("userToUpdate") User user,
                         @RequestParam(value = "role") String role) {
        userService.updateWithRole(user, role);
        return "redirect:/admin/listOfUsers";
    }

    @DeleteMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/listOfUsers";
    }
}
