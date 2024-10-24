package com.example.Account_microservice.user.convert.mapper.role;


import com.example.Account_microservice.user.dto.role.RoleDto;
import com.example.Account_microservice.user.model.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperRole.class)
public interface MapperListRole {

    Set<Role> toModel(Set<String> roles);
    Set<RoleDto> toDto(Set<Role> roles);

}
