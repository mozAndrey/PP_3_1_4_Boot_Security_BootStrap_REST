package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Secured(value = {"ROLE_ADMIN"})
public class MyController {
    @GetMapping(value = "/admin")
    public String admin() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userHome";
    }
}
