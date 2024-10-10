package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HistoryDataValidateImpl implements HistoryDataValidate {

    private final AccountAndRoleOfAccountValidator accountValidator;
    private final HospitalAndRoomValidate hospitalAndRoomValidate;
    @Override
    public void validate(String authorizationHeader, RequestHistoryDto dto) {
        accountValidator.validateAccount(authorizationHeader, dto);
            hospitalAndRoomValidate.validateHospitalAndRoom(authorizationHeader, dto);
    }

    @Override
    public void verification(String authorizationHeader, Long pacientId) {
        accountValidator.verificationBelongsToUserHistory(authorizationHeader, pacientId);
    }
}
