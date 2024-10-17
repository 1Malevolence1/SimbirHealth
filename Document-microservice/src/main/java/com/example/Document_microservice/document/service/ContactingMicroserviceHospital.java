package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.dto.ResponseRoomsHospitalDto;

public interface ContactingMicroserviceHospital {

    ResponseRoomsHospitalDto requestCheckHospital(String token, Long hospitalId);
}
