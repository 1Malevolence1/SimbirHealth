package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.dto.RequestHistoryDto;

public interface HospitalAndRoomValidate {

    void validateHospitalAndRoom(String token, RequestHistoryDto dto);
}
