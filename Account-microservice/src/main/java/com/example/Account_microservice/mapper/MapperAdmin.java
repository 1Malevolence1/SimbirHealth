package com.example.Account_microservice.mapper;


import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MapperListRole.class)
public interface MapperAdmin {

    User toModel(RequestAdminSaveAccount dto);
}
