package com.example.Hospital_microservice.hospital.convert.mapper;


import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.model.Hospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MapperHospitalListRooms.class)
public interface MapperHospital {

    Hospital toModel(RequestCreateHospitalDto requestCreateHospitalDto);
    ResponseHospitalDto toDto(Hospital hospital);

}
