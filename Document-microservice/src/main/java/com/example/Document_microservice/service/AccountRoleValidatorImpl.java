package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AccountRoleValidatorImpl implements AccountRoleValidator {

    private final CheckerDataAccount checkerMicroserviceAccount;

    @Override
    public void validateAccount(String authorizationHeader, RequestHistoryDto dto) {
        if(dto.pacientId() != null) checkerMicroserviceAccount.checkPacient(authorizationHeader, dto.pacientId());
        if(dto.doctorId() != null)  checkerMicroserviceAccount.checkDoctor(authorizationHeader, dto.doctorId());
    }
}
