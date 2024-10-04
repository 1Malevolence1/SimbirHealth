package com.example.Hospital_microservice.hospital.convert.manager;


import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.RequestUpdateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.model.Hospital;

import java.util.List;

public interface ManagerMapperHospital {

    Hospital toModel(RequestCreateHospitalDto requestCreateHospitalDto);
    Hospital toModel(RequestUpdateHospitalDto requestCreateHospitalDto);
    ResponseHospitalDto toDto(Hospital hospital);
    List<ResponseHospitalDto> toDto(List<Hospital> hospitals);
}
