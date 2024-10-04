package com.example.Hospital_microservice.hospital.service.admin;

import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.model.Hospital;

import java.util.List;

public interface AdminService {

    void addHospital(RequestCreateHospitalDto requestCreateHospitalDto);

    List<ResponseHospitalDto> getAllHospitals(Integer from, Integer count);
}
