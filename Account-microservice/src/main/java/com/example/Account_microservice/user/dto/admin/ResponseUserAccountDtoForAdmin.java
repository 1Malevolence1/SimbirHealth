package com.example.Account_microservice.user.dto.admin;

import com.example.Account_microservice.user.dto.role.RoleDto;

import java.util.Set;

public record ResponseUserAccountDtoForAdmin(
        Long id,
        String lastName,
        String firstName,
        String username,
        Set<RoleDto> roles
) {
}
