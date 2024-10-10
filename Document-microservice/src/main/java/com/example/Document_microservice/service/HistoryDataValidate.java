package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;

public interface HistoryDataValidate {

    void validate(String authorizationHeader, RequestHistoryDto dto);
    void verification(String authorizationHeader, Long pacientId);

}
