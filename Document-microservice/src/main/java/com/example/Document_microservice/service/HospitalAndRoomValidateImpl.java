package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.RequestHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HospitalAndRoomValidateImpl implements HospitalAndRoomValidate {

    private final CheckerDataHospital checkerDataHospital;

    @Override
    public void validateHospitalAndRoom(String token, RequestHistoryDto dto) {
        checkerDataHospital.checkHospital(token, dto.hospitalId(), dto.room());
    }
}
