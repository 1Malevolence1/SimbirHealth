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
        checkerMicroserviceAccount.checkPacient(authorizationHeader, dto.pacientId());
        checkerMicroserviceAccount.checkDoctor(authorizationHeader, dto.doctorId());
    }
}
