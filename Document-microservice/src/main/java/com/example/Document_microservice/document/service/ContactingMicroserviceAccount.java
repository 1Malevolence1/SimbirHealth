package com.example.Document_microservice.document.service;

import java.util.List;

public interface ContactingMicroserviceAccount {

    List<String> requestForRoleRecognition(String token, Long accountId);
    Long requestDecodingJwtTokenIdUser(String token);

}
