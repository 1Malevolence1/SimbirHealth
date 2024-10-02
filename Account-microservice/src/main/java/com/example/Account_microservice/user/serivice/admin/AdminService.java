package com.example.Account_microservice.user.serivice.admin;

import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.model.User;

import java.util.List;

public interface AdminService {

    void save(RequestAdminSaveAccount requestAdminSaveAccount);

    void update(RequestAdminUpdateAccount requestUpdateAccountDto, Long id);

    void deleteById(Long id);

    List<User> findUsersFromOffsetWithLimit(Integer from, Integer count);
}
