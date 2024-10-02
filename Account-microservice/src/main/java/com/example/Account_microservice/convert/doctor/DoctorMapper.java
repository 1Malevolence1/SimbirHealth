package com.example.Account_microservice.convert.doctor;


import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    ResponseDoctorDto toDTO(User user);
}
