package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.dto.RequestHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HospitalAndRoomValidateImpl implements HospitalAndRoomValidate {

    private final CheckerDataHospital checkerDataHospital;

    @Override
    public void validateHospitalAndRoom(String token, RequestHistoryDto dto) {
        if(dto.hospitalId() != null) checkerDataHospital.checkHospital(token, dto.hospitalId(), dto.room());

    }
}
