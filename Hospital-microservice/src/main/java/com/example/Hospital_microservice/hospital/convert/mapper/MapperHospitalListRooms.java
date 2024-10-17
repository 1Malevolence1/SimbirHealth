package com.example.Hospital_microservice.hospital.convert.mapper;


import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.model.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapperHospitalRoom.class, MapperHospitalRoom.class})
public interface MapperHospitalListRooms {

    List<Room> toModel(List<String> rooms);
    List<ResponseHospitalRoomsDto> toDto(List<Room> rooms);
}
