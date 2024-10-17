package com.example.Account_microservice.user.service.guest_user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoelServiceImpl implements RoelService {

    private final RoleRepository roleRepository;
    @Override
    public Long getRoleIdByRoleName(String roleName) {
        return roleRepository.findIdByRoleName(roleName);
    }
}
