package com.example.Document_microservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactingMicroserviceAccountImpl implements ContactingMicroserviceAccount {
    
    private final RestClient restClientMicroserviceAccount;

    @Override
    public List<String> requestForRoleRecognition(String token, Long accountId) {

        ResponseEntity<List<String>> roles = restClientMicroserviceAccount
                .get()
                .uri(
                "/api/account/roles/%d".formatted(accountId)).header("Authorization", "Bearer " + token)
                .retrieve().toEntity(new ParameterizedTypeReference<List<String>>() {
                });

        return roles.getBody();
    }
}
