package com.example.Account_microservice.convert.manager_mapper;

import com.example.Account_microservice.convert.mapper.MapperListRole;
import com.example.Account_microservice.convert.mapper.MapperRole;
import com.example.Account_microservice.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ManagerMapperRoleImpl implements ManagerMapperRole {

    private final MapperRole mapperRole;
    private final MapperListRole mapperListRole;

    @Override
    public Role toModel(String role) {
        return mapperRole.toMODEL(role);
    }

    @Override
    public Set<Role> toSetModel(Set<String> roles) {
        return mapperListRole.toModel(roles);
    }
}
