package com.example.Account_microservice.user.convert.mapper.role;


import com.example.Account_microservice.user.dto.role.RoleDto;
import com.example.Account_microservice.user.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperRole {


    @Mapping(target = "id", expression = "java(RoleSettingId.getId(roleName))")
    Role toMODEL(String roleName);

    RoleDto toDto(Role role);

}
