package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.dto.RequestHistoryDto;

public interface HistoryDataValidate {

    void validate(String authorizationHeader, RequestHistoryDto dto);
    void verification(String authorizationHeader, Long pacientId);

}
