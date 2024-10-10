package com.example.Document_microservice.service;

import com.example.Document_microservice.config.ConstantResponseExceptionText;
import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.exeption.TheUserHasSeveralRoles;
import com.example.Document_microservice.exeption.UserDoesNotHaveARole;
import com.example.Document_microservice.exeption.Validate;
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
        if (list.contains("ROLE_USER") && list.size() == 1) {
            checkerMicroserviceAccount.verificationUser(pacientId, userId);
            return;
        }

        if(!list.contains("ROLE_DOCTOR"))
            throw new UserDoesNotHaveARole(new Validate(ConstantResponseExceptionText.USER_DOES_NOT_HAVE_ROLE_DOCTOR));
    }
}
