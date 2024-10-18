package com.example.Account_microservice.user.convert.manager_mapper;

import com.example.Account_microservice.user.convert.mapper.user.MapperListUser;
import com.example.Account_microservice.user.convert.mapper.user.MapperUser;
import com.example.Account_microservice.user.dto.RequestUpdateUserAccountDto;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
import com.example.Account_microservice.user.dto.admin.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.admin.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
import com.example.Account_microservice.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ManagerMapperAccountImpl implements ManagerMapperAccount {

    private final MapperUser mapperUser;
    private final MapperListUser mapperListUser;


    @Override
    public ResponseUserAccountDto toDto(User user) {
        return mapperUser.toDTO(user);
    }

    @Override
    public List<ResponseUserAccountDto> toDtoListAccount(List<User> users) {
        return mapperListUser.toDTO(users);
    }

    @Override
    public User toModelFormSingUpGuestUser(RequestSingInGuestUserDto singUpGuestDto) {
        return mapperUser.toModelFromSingUpGuestUser(singUpGuestDto);
    }

    @Override
    public User toModelFromUpdate(RequestUpdateUserAccountDto updateAccountDto) {
        return mapperUser.toModelFromUpdateAccount(updateAccountDto);
    }

    @Override
    public User toModelFromAdminUpdate(RequestAdminUpdateAccount adminUpdateAccount) {
        return mapperUser.toModelFromAdminUpdate(adminUpdateAccount);
    }


    @Override
    public User toModelFromAdminSave(RequestAdminSaveAccount adminSaveAccount) {
        return mapperUser.toModelFromAdminSave(adminSaveAccount);
    }
}
