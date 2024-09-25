package com.example.Account_microservice.user.serivice;

import com.example.Account_microservice.user.dto.RequestSingUpUserDto;
import com.example.Account_microservice.user.model.User;

import java.util.List;

public interface UserService {

    User save(RequestSingUpUserDto dto);
    User findUser(String username);
    List<User> findAll();


}
