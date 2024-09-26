package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.model.User;

public final class ConvertUser {

    public static User toModel(RequestSingUpAccountDto dto){
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .password(dto.password())
                .build();
    }
}
