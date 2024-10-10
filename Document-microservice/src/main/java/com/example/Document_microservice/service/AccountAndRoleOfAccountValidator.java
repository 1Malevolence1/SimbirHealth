package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;

public interface AccountAndRoleOfAccountValidator {

    void validateAccount(String authorizationHeader, RequestHistoryDto dto);

    void verificationBelongsToUserHistory(String authorizationHeader, Long pacientId);
}
