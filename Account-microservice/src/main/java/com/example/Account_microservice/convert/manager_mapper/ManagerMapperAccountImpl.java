package com.example.Account_microservice.convert.manager_mapper;

import com.example.Account_microservice.convert.mapper.MapperListUser;
import com.example.Account_microservice.convert.mapper.MapperUser;
import com.example.Account_microservice.user.dto.*;
import com.example.Account_microservice.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ManagerMapperAccountImpl implements ManagerMapperAccount {
    private final MapperUser mapperUser;
    private final MapperListUser mapperListUser;
    private final ManagerMapperRole managerMapperRole;

    @Override
    public ResponseAccountDto toDto(User user) {
        return mapperUser.toDTO(user);
    }

    @Override
    public List<ResponseAccountDto> toDtoListAccount(List<User> users) {
        return mapperListUser.toDTO(users);
    }

    @Override
    public User toModelFromSignUp(RequestSingUpAccountDto signUpDto) {
        return mapperUser.toModelFromSingUP(signUpDto);
    }

    @Override
    public User toModelFromUpdate(RequestUpdateAccountDto updateAccountDto) {
        return mapperUser.toModelFromUpdateAccount(updateAccountDto);
    }

    @Override
    public User toModelFromAdminUpdate(RequestAdminUpdateAccount adminUpdateAccount) {
        User user = mapperUser.toModelFromAdminUpdate(adminUpdateAccount);
        user.setRoles(
                managerMapperRole.toSetModel(adminUpdateAccount.roles())
        );
        return user;
    }


    @Override
    public User toModelFromAdminSave(RequestAdminSaveAccount adminSaveAccount) {

      User user = mapperUser.toModelFromAdminSave(adminSaveAccount);
      user.setRoles(
              managerMapperRole.toSetModel(adminSaveAccount.roles())
      );
      return user;
    }
}
