package com.example.Account_microservice.user.serivice;

import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(RequestSingUpAccountDto singUpDto);

    User findUserByUsername(String username);

    User findUserById(Long id);

    Optional<User> findUserByIdReturnOptional(Long id);

    void save(User user);

    void update(User user, Long id);

    void deleteById(Long id);

}
