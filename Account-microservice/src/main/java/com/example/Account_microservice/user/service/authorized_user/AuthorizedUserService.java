package com.example.Account_microservice.user.service.authorized_user;

import com.example.Account_microservice.user.dto.RequestUpdateUserAccountDto;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;

public interface AuthorizedUserService {

    ResponseUserAccountDto findUserById(Long id);

    void update(RequestUpdateUserAccountDto updateAccountDto, Long id);
}
