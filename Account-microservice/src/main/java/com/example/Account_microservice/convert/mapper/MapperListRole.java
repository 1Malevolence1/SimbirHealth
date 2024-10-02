package com.example.Account_microservice.convert.mapper;


import com.example.Account_microservice.user.model.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperRole.class)
public interface MapperListRole {

    Set<Role> toModel(Set<String> roles);
}
