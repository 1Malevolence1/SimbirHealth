package com.example.Hospital_microservice.hospital.convert.manager;

import com.example.Hospital_microservice.hospital.convert.mapper.MapperHospital;
import com.example.Hospital_microservice.hospital.convert.mapper.MapperListHospitals;
import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.model.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ManagerMapperHospitalImpl implements ManagerMapperHospital {

    private final MapperHospital mapperHospital;
    private final MapperListHospitals mapperListHospitals;

    @Override
    public Hospital toModel(RequestCreateHospitalDto requestCreateHospitalDto) {
        return mapperHospital.toModel(requestCreateHospitalDto);
    }

    @Override
    public List<ResponseHospitalDto> toDto(List<Hospital> hospitals) {
        return mapperListHospitals.toDto(hospitals);
    }

}
