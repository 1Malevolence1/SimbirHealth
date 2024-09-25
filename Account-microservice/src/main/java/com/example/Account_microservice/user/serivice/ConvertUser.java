package com.example.Account_microservice.user.serivice;

import com.example.Account_microservice.user.dto.RequestSingUpUserDto;
import com.example.Account_microservice.user.model.User;

public final class ConvertUser {

    public static User toModel(RequestSingUpUserDto dto){
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .password(dto.password())
                .roles(ConvertRoles.toModel(dto.roles()))
                .build();
    }
}
