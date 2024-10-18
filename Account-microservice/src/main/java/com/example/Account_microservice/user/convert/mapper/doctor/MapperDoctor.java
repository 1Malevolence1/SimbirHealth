package com.example.Account_microservice.user.convert.mapper.doctor;


import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperDoctor {

    ResponseDoctorDto toDTO(User user);
}
