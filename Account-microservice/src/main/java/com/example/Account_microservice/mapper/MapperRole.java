package com.example.Account_microservice.mapper;


import com.example.Account_microservice.user.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperRole {


    Role toMODEL(String roleName);

}
