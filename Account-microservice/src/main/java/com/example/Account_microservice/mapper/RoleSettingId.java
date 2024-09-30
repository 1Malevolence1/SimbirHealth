package com.example.Account_microservice.mapper;


import com.example.Account_microservice.config.ConstantResponseText;
import com.example.Account_microservice.exception.BadRequestRolesException;
import com.example.Account_microservice.exception.Validate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public  final class RoleSettingId {


    public static Long getId(String role) {
        Map<Long, String> map = Map.of(1L, "ROLE_USER", 2L, "ROLE_ADMIN");

        for (Map.Entry<Long, String> entry : map.entrySet()) {
            if (entry.getValue().equals(role)) {
                return entry.getKey();
            }
        }
        throw new BadRequestRolesException(new Validate(ConstantResponseText.NOT_FOUND_ROLE_EXCEPTION));
    }
}
