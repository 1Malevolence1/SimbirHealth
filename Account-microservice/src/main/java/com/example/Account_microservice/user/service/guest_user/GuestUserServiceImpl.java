package com.example.Account_microservice.user.service.guest_user;

import com.example.Account_microservice.user.convert.manager_mapper.ManagerMapperAccount;
import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class GuestUserServiceImpl implements GuestUserService {

    private final UserService userService;
    private final ManagerMapperAccount managerMapperAccount;
;

    @Override
    public User addAccount(RequestSingInGuestUserDto dto) {
        User user = managerMapperAccount.toModelFormSingUpGuestUser(dto);
        Role role = Role.builder().id(1L).roleName("ROLE_USER").build();
        user.setRoles(Set.of(role));
        return userService.save(user);
    }


}
