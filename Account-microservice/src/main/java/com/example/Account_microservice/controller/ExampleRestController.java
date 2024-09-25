package com.example.Account_microservice.controller;


import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.serivice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleRestController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getStringAdmin(){
        return "Строку мог запросить только админ";
    }

    @GetMapping("/user")

    public String getStringUser(){
        return "Строку мог запросить только авторизированный пользователь";
    }
}
