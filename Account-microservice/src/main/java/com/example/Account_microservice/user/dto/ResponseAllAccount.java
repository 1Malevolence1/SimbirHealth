package com.example.Account_microservice.user.dto;

import com.example.Account_microservice.user.model.User;

import java.util.List;

public record ResponseAllAccount(
        List<User> accounts
) {

}
