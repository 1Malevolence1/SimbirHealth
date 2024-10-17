package com.example.Hospital_microservice.hospital.service.room;

import com.example.Hospital_microservice.hospital.model.Room;

import java.util.List;

public interface HospitalRoomsService {

    List<Room> getHospitalRoomsByIdHospital(Long id);

    void deleteAllRoomsForHospital(Long id);
}
