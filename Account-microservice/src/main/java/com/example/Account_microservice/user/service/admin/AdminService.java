package com.example.Account_microservice.user.service.admin;

import com.example.Account_microservice.user.dto.admin.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.admin.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;

import java.util.List;

public interface AdminService {

    void save(RequestAdminSaveAccount requestAdminSaveAccount);

    void update(RequestAdminUpdateAccount requestUpdateAccountDto, Long id);

    void deleteById(Long id);

    List<ResponseUserAccountDto> getAll(Integer from, Integer count);
}