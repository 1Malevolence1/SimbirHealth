package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.dto.RequestHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AccountAndRoleOfAccountValidatorImpl implements AccountAndRoleOfAccountValidator {

    private final CheckerDataAccount checkerMicroserviceAccount;
    private final ContactingMicroserviceAccount contactingMicroserviceAccount;

    @Override
    public void validateAccount(String authorizationHeader, RequestHistoryDto dto) {
        if(dto.pacientId() != null) checkerMicroserviceAccount.checkPacient(authorizationHeader, dto.pacientId());
        if(dto.doctorId() != null)  checkerMicroserviceAccount.checkDoctor(authorizationHeader, dto.doctorId());
    }




    @Override
    public void verificationBelongsToUserHistory(String authorizationHeader, Long pacientId) {
        Long userId = contactingMicroserviceAccount.requestDecodingJwtTokenIdUser(authorizationHeader);
        List<String> list = contactingMicroserviceAccount.requestForRoleRecognition(authorizationHeader, userId);

        if(list.contains("ROLE_DOCTOR")) return;

        checkerMicroserviceAccount.verificationUser(pacientId, userId);
    }
}
