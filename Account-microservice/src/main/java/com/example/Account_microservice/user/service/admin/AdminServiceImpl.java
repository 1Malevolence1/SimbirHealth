package com.example.Account_microservice.user.service.admin;

import com.example.Account_microservice.convert.manager_mapper.ManagerMapperAccount;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
import com.example.Account_microservice.user.dto.admin.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.admin.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final ManagerMapperAccount managerMapperAccount;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void save(RequestAdminSaveAccount requestAdminSaveAccount) {
        userService.save(
                managerMapperAccount.toModelFromAdminSave(requestAdminSaveAccount)
        );
    }

    @Override
    public void update(RequestAdminUpdateAccount requestUpdateAccountDto, Long id) {
        User user = managerMapperAccount.toModelFromAdminUpdate(requestUpdateAccountDto);
        userService.update(user, id);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }


    @Override
    public List<ResponseUserAccountDto> getAll(Integer from, Integer count) {
        if(count == null) return managerMapperAccount.toDtoListAccount(userService.getUsersFromOffset(from));
        return managerMapperAccount.toDtoListAccount(userService.getUsersFromOffsetWithLimit(from, count));

    }
}
