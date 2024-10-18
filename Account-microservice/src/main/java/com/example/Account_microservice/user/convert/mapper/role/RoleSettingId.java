package com.example.Account_microservice.user.convert.mapper.role;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.user.exception.BadRequestRolesException;
import com.example.Account_microservice.user.exception.Validate;
import org.springframework.stereotype.Component;

@Component
public class RoleSettingId {


    public static Long getId(String role) {
        return switch (role){
            case "ROLE_USER" -> 1L;
            case "ROLE_ADMIN" -> 2L;
            case "ROLE_DOCTOR" -> 3L;
            case "ROLE_MANAGER" -> 4L;
            default ->
                    throw new BadRequestRolesException(new Validate(ConstantResponseExceptionText.NOT_FOUND_ROLE_EXCEPTION.formatted(role)));
        };
    }
}
