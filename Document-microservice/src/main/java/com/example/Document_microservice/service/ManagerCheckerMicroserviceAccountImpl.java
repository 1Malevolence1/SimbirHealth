package com.example.Document_microservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagerCheckerMicroserviceAccountImpl implements ManagerCheckerMicroserviceAccount {

    private final ContactingMicroserviceAccount contactingMicroserviceAccount;


    @Override
    public boolean roleMatchingCheck(String token, Long accountId) {
        String role = getRole(
                contactingMicroserviceAccount.requestForRoleRecognition(token)
        );


        return false;
    }


    private boolean checkSizeListRoles(List<String> roles){
        return roles.size() == 1;
    }

    private String getRole(List<String> roles){
        if(checkSizeListRoles(roles)){
            return roles.get(0);
        }
        else return null;
    }
}
