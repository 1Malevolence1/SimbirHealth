package com.example.Account_microservice.user.serivice.admin;

import com.example.Account_microservice.convert.manager_mapper.ManagerMapperAccount;
import com.example.Account_microservice.convert.manager_mapper.ManagerMapperRole;
import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
import com.example.Account_microservice.user.serivice.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ManagerMapperAccount managerMapperAccount;
    private final ManagerMapperRole managerMapperRole;


    @Override

    public void save(RequestAdminSaveAccount requestAdminSaveAccount) {
        userService.save(
                managerMapperAccount.toModelFromAdminSave(requestAdminSaveAccount)
        );
    }

    @Override
    public void update(RequestAdminUpdateAccount requestUpdateAccountDto, Long id) {
        User user = managerMapperAccount.toModelFromAdminUpdate(requestUpdateAccountDto);
        user.setRoles(managerMapperRole.toSetModel(requestUpdateAccountDto.roles()));
        userService.update(user, id);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }


    @Override
    public List<User> findUsersFromOffsetWithLimit(Integer form, Integer count) {
        return userRepository.getUsersFromOffsetWithLimit(form, count);
    }
}
