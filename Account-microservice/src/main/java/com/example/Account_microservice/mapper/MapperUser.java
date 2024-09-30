package com.example.Account_microservice.mapper;


import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUser {

    ResponseAccountDto toDTO(User user);
    User toModel(RequestSingUpAccountDto dto);
}
