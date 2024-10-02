package com.example.Account_microservice.user.serivice.authorized_user;

import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.model.User;

public interface AuthorizedUser {

    ResponseAccountDto findUserById(Long id);

    void update(RequestUpdateAccountDto updateAccountDto, Long id);
}
