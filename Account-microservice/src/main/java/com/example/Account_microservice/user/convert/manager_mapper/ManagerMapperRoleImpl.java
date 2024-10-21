package com.example.Account_microservice.user.convert.manager_mapper;

import com.example.Account_microservice.user.convert.mapper.role.MapperListRole;
import com.example.Account_microservice.user.convert.mapper.role.MapperRole;
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
