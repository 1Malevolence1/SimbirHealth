package com.example.Account_microservice.user.service.authorized_user;

import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.dto.ResponseAccountDto;

public interface AuthorizedUserService {

    ResponseAccountDto findUserById(Long id);

    void update(RequestUpdateAccountDto updateAccountDto, Long id);
}
