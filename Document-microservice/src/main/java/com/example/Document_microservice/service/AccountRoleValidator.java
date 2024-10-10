package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;

public interface AccountRoleValidator {

    void validateAccount(String authorizationHeader, RequestHistoryDto dto);
}
