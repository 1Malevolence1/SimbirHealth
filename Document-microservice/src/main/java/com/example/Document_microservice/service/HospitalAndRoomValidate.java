package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;

public interface HospitalAndRoomValidate {

    void validateHospitalAndRoom(String token, RequestHistoryDto dto);
}
