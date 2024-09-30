package com.example.Account_microservice.mapper;


import com.example.Account_microservice.user.model.Role;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperRole.class)
public interface MapperListRole {

    Set<Role> toModel(Set<String> roles);
}
