package com.example.Account_microservice.convert.manager_mapper;

import com.example.Account_microservice.user.dto.*;
import com.example.Account_microservice.user.dto.admin.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.admin.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
import com.example.Account_microservice.user.model.User;

import java.util.List;

public interface ManagerMapperAccount {

    ResponseUserAccountDto toDto(User user);
    List<ResponseUserAccountDto> toDtoListAccount(List<User> users);

    User toModelFormSingUpGuestUser(RequestSingInGuestUserDto singUpGuestDto);
    User toModelFromSignUp(RequestSingUpUserAccountDto signUpDto);
    User toModelFromUpdate(RequestUpdateUserAccountDto updateAccountDto);
    User toModelFromAdminUpdate(RequestAdminUpdateAccount adminUpdateAccount);
    User toModelFromAdminSave(RequestAdminSaveAccount adminSaveAccount);
}
