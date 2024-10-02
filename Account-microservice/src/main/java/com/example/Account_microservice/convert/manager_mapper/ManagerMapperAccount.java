package com.example.Account_microservice.convert.manager_mapper;

import com.example.Account_microservice.user.dto.*;
import com.example.Account_microservice.user.model.User;

import java.util.List;

public interface ManagerMapperAccount {

    ResponseAccountDto toDto(User user);
    List<ResponseAccountDto> toDtoListAccount(List<User> users);

    User toModelFromSignUp(RequestSingUpAccountDto signUpDto);
    User toModelFromUpdate(RequestUpdateAccountDto updateAccountDto);
    User toModelFromAdminUpdate(RequestAdminUpdateAccount adminUpdateAccount);
    User toModelFromAdminSave(RequestAdminSaveAccount adminSaveAccount);
}
