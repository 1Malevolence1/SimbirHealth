package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
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

    public static ResponseAccountDto toDTO(User user){
        return new ResponseAccountDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername()
        );
    }

}
