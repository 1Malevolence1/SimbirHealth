package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.dto.RequestHistoryDto;

public interface AccountAndRoleOfAccountValidator {

    void validateAccount(String authorizationHeader, RequestHistoryDto dto);

    void verificationBelongsToUserHistory(String authorizationHeader, Long pacientId);
}
