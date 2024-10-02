package com.example.Account_microservice.user.service.admin;

import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.ResponseAccountDto;

import java.util.List;

public interface AdminService {

    void save(RequestAdminSaveAccount requestAdminSaveAccount);

    void update(RequestAdminUpdateAccount requestUpdateAccountDto, Long id);

    void deleteById(Long id);

    List<ResponseAccountDto> getAll(Integer from, Integer count);
}
