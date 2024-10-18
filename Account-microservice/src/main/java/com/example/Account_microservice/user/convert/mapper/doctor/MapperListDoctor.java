package com.example.Account_microservice.user.convert.mapper.doctor;


import com.example.Account_microservice.user.dto.doctor.ResponseDoctorDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperDoctor.class)
public interface MapperListDoctor {

    List<ResponseDoctorDto> toDTO(List<User> user);
}
