package com.example.Account_microservice.mapper.doctor;


import com.example.Account_microservice.user.dto.ResponseDoctorDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DoctorMapper.class)
public interface DoctorListMapper {

    List<ResponseDoctorDto> toDTO(List<User> user);
}
