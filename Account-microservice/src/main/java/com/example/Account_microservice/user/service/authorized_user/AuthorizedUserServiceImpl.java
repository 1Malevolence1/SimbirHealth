package com.example.Account_microservice.user.service.authorized_user;

import com.example.Account_microservice.user.convert.manager_mapper.ManagerMapperAccount;
import com.example.Account_microservice.user.dto.RequestUpdateUserAccountDto;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final UserService userService;
    private final ManagerMapperAccount managerMapperAccount;

    @Override
    public ResponseUserAccountDto findUserById(Long id) {
        return managerMapperAccount.toDto(userService.findUserById(id));
    }


    @Override
    public void update(RequestUpdateUserAccountDto updateAccountDto, Long id) {
        log.info("Начался метод по обноволению данных текущего пользователя");
        log.info("Данные для обновления: {}", updateAccountDto);
        userService.update(
                managerMapperAccount.toModelFromUpdate(updateAccountDto),
                id
        );
    }

}
