package com.example.Account_microservice.user.convert.manager_mapper;

import com.example.Account_microservice.user.model.Role;

import java.util.Set;

public interface ManagerMapperRole {

    Role toModel(String role);

    Set<Role> toSetModel(Set<String> roles);
}
