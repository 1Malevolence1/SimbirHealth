package com.example.Document_microservice.service;

import com.example.Document_microservice.dto.ResponseRoomsHospitalDto;

public interface ContactingMicroserviceHospital {

    ResponseRoomsHospitalDto requestCheckHospital(String token, Long hospitalId);
}
