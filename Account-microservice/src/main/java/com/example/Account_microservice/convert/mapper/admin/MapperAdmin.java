package com.example.Account_microservice.convert.mapper.admin;


import com.example.Account_microservice.convert.mapper.role.MapperListRole;
import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MapperListRole.class)
public interface MapperAdmin {

    User toModel(RequestAdminSaveAccount dto);
}
