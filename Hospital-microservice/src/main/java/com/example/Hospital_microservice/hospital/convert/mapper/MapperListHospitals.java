package com.example.Hospital_microservice.hospital.convert.mapper;


import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.model.Hospital;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapperHospital.class, MapperHospitalListRooms.class})
public interface MapperListHospitals {

    List<ResponseHospitalDto> toDto(List<Hospital> hospitals);
}
