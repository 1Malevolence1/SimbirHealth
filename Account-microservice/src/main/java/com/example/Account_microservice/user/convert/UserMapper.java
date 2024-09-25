package com.example.Account_microservice.user.convert;


import com.example.Account_microservice.user.dto.RequestSingUpUserDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(RequestSingUpUserDto dto);
}
