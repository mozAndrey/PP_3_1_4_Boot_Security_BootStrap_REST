package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.configs.SuccessUserHandler;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
@Secured(value = {"ROLE_ADMIN"})
public class AdminController {
    private final UserDetailsServiceImpl userService;
    private final RoleService roleService;
    private User user;

    @Autowired
    public AdminController(UserDetailsServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/listOfUsers/{adminInfo}")
    public String listOfUsersWithAdminInfo(Model modelMap,
                                           @ModelAttribute("user") User user,
                                           @PathVariable("adminInfo") String username) {
        this.user = userService.findByUserName(username);
        modelMap.addAttribute("listOfUsers", userService.getListUsers());
        modelMap.addAttribute("userRole", roleService.findRole("ROLE_USER"));
        modelMap.addAttribute("adminRole", roleService.findRole("ROLE_ADMIN"));
        modelMap.addAttribute("adminInfo", this.user);
        return "index";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role") String[] roles) {
        userService.addUserWithRole(user, roles);
        return "redirect:/admin/listOfUsers/" + this.user.getUsername();
    }

    @PatchMapping(value = "/update/u")
    public String update(@ModelAttribute("userToUpdate") User user,
                         @RequestParam(value = "role") String[] role) {
        userService.updateWithRole(user, role);
        return "redirect:/admin/listOfUsers/" + this.user.getUsername();
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/listOfUsers/" + this.user.getUsername();
    }
}
