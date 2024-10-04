package com.example.Hospital_microservice.hospital.convert.manager;

import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.model.Room;

import java.util.List;

public interface ManagerMapperHospitalRooms {

    List<ResponseHospitalRoomsDto> toDto(List<Room> rooms);
}
