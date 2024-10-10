package com.example.Document_microservice.service;


import com.example.Document_microservice.config.ConstantResponseExceptionText;
import com.example.Document_microservice.exeption.TheStoryDoesNotBelongToThisUser;
import com.example.Document_microservice.exeption.TheUserHasSeveralRoles;
import com.example.Document_microservice.exeption.UserDoesNotHaveARole;
import com.example.Document_microservice.exeption.Validate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckerDataAccountImpl implements CheckerDataAccount {

    private final ContactingMicroserviceAccount contactingMicroserviceAccount;


    private String getRole(List<String> roles, Long accountId) {
        log.info("{}", roles);
        if (roles.size() == 1) {
            return roles.get(0);
        } else
            throw new TheUserHasSeveralRoles(new Validate(ConstantResponseExceptionText.ROLES_MORE_ONE.formatted(accountId)));
    }

    @Override
    public void checkPacient(String token, Long accountId) {
        String role = getRole(
                contactingMicroserviceAccount.requestForRoleRecognition(token, accountId),
                accountId
        );

        if (!Objects.equals(role, "ROLE_USER")) {
            throw new UserDoesNotHaveARole(new Validate(
                    ConstantResponseExceptionText.USER_DOES_NOT_HAVE_THIS_ROLE.formatted(accountId, "ROLE_USER")
            ));
        }
    }

    @Override
    public void checkDoctor(String token, Long accountId) {
        List<String> role = contactingMicroserviceAccount.requestForRoleRecognition(token, accountId);
        if (!role.contains("ROLE_DOCTOR"))
            throw new UserDoesNotHaveARole(new Validate(ConstantResponseExceptionText.USER_DOES_NOT_HAVE_THIS_ROLE.formatted(accountId, "ROLE_DOCTOR")));
    }

    @Override
    public void verificationUser(Long pacientIdOfHistory, Long userId) {
       if(!Objects.equals(pacientIdOfHistory, userId)) throw
               new TheStoryDoesNotBelongToThisUser(
                       new Validate(
                               ConstantResponseExceptionText.ID_NOT_BELONG_USER.formatted(userId)

       ));
    }
}
