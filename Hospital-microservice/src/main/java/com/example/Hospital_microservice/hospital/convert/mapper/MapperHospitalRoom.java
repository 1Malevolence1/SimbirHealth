package com.example.Hospital_microservice.hospital.convert.mapper;


import com.example.Hospital_microservice.hospital.dto.ResponseRoomsDto;
import com.example.Hospital_microservice.hospital.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperHospitalRoom {

    Room toModel(String title);
    ResponseRoomsDto toDto(Room room);
}
