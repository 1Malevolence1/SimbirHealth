package com.example.Account_microservice.mapper;


import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperUser {

    ResponseAccountDto toDTO(User user);
    User toModel(RequestSingUpAccountDto dto);


}
