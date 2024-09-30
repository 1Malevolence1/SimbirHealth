package com.example.Account_microservice.user.serivice;

import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.model.User;

import java.util.List;

public interface UserService {

    User save(RequestSingUpAccountDto singUpDto);

    User findUserByUsername(String username);
    User findUserById(Long id);
    List<User> findUsersFromOffsetWithLimit(Integer from, Integer count);
    void update(RequestUpdateAccountDto updateAccountDto, Long id);

}
