package com.example.Document_microservice.service;


import com.example.Document_microservice.config.ConstantResponseExceptionText;
import com.example.Document_microservice.dto.ResponseRoleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContactingMicroserviceAccountImpl implements ContactingMicroserviceAccount {

    private final RestClient restClientMicroserviceAccount;

    @Override
    public List<String> requestForRoleRecognition(String token, Long accountId) {

        try {
            ResponseEntity<ResponseRoleUser> roles = restClientMicroserviceAccount
                    .get()
                    .uri(
                            "/api/jwt/roles/%d".formatted(accountId)).header("Authorization", token)
                    .retrieve().toEntity(ResponseRoleUser.class);
            return Objects.requireNonNull(roles.getBody()).roles();
        } catch (HttpClientErrorException exception){
          if(exception.getStatusCode() == HttpStatus.NOT_FOUND) throw new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(accountId));
        }

        return List.of();
    }
}