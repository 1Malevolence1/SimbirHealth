package com.example.Account_microservice.controller;


import com.example.Account_microservice.jwt.black_list.model.BlackListToken;
import com.example.Account_microservice.jwt.black_list.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/token")
@RequiredArgsConstructor
public class BackListRestController {

    private final BlackListRepository blackListRepository;

    @GetMapping("/all")
    public List<BlackListToken> findAllBlackToken(){
        return blackListRepository.findAll();
    }
}
