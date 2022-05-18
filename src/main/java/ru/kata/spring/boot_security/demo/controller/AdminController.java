package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.CustomUserDetailsService;
import ru.kata.spring.boot_security.demo.service.RoleService;


@Controller
@RequestMapping("/admin")
@Secured(value = {"ROLE_ADMIN"})
public class AdminController {
    private final CustomUserDetailsService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(CustomUserDetailsService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/listOfUsers")
    public String listOfUsers(Model modelMap) {
        modelMap.addAttribute("listOfUsers", userService.getListUsers());
        return "users";
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(@ModelAttribute("user") User user,
                             Model model) {
        model.addAttribute("userRole", roleService.findRole("ROLE_USER"));
        model.addAttribute("adminRole", roleService.findRole("ROLE_ADMIN"));
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role") String[] roles) {
        userService.addUserWithRole(user, roles);
        return "redirect:/admin/listOfUsers";
    }

    @GetMapping(value = "/update")
    public String updateUser(Model model, @RequestParam("idToUpdate") Long id) {
        model.addAttribute("userToUpdate", userService.findById(id));
        model.addAttribute("userRole", roleService.findRole("ROLE_USER"));
        model.addAttribute("adminRole", roleService.findRole("ROLE_ADMIN"));
        return "update";
    }

    @PatchMapping(value = "/update/u")
    public String update(@ModelAttribute("userToUpdate") User user,
                         @RequestParam(value = "role") String[] role) {
        userService.updateWithRole(user, role);
        return "redirect:/admin/listOfUsers";
    }

    @DeleteMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/listOfUsers";
    }
}
