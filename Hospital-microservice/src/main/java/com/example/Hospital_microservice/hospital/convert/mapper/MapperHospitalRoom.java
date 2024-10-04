package com.example.Hospital_microservice.hospital.convert.mapper;


import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperHospitalRoom {

    Room toModel(String title);
    ResponseHospitalRoomsDto toDto(Room room);
}
