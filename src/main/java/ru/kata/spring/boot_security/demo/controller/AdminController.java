package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
@Secured(value = {"ROLE_ADMIN"})
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private UserDetails userDetails;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/listOfUsers/{adminInfo}")
    public String listOfUsersWithAdminInfo(Model modelMap,
                                           @ModelAttribute("user") User user,
                                           @PathVariable("adminInfo") String username) {
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails = userDetailsService.loadUserByUsername(userDetails.getUsername());
        modelMap.addAttribute("listOfUsers", userService.getListUsers());
        modelMap.addAttribute("userRole", roleService.findRole("ROLE_USER"));
        modelMap.addAttribute("adminRole", roleService.findRole("ROLE_ADMIN"));
        modelMap.addAttribute("adminInfo", this.userDetails);
        return "index";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role") String[] roles) {
        userService.addUserWithRole(user, roles);
        return "redirect:/admin/listOfUsers/" + this.userDetails.getUsername();
    }

    @PatchMapping(value = "/update/u")
    public String update(@ModelAttribute("userToUpdate") User user,
                         @RequestParam(value = "role") String[] role) {
        userService.updateWithRole(user, role);
        return "redirect:/admin/listOfUsers/" + this.userDetails.getUsername();
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/listOfUsers/" + this.userDetails.getUsername();
    }
}
