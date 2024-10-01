package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.mapper.MapperAdmin;
import com.example.Account_microservice.mapper.MapperListRole;
import com.example.Account_microservice.mapper.MapperUser;
import com.example.Account_microservice.user.dto.RequestAdminSaveAccount;
import com.example.Account_microservice.user.dto.RequestAdminUpdateAccount;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperUser mapperUser;
    private final MapperAdmin mapperAdmin;
    private final MapperListRole mapperListRole;


    @Override
    @Transactional
    public User save(RequestSingUpAccountDto dto) {
        User user = mapperUser.toModel(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.builder().id(1L).roleName("ROLE_USER").build()));
        return userRepository.save(user);

    }

    @Override
    @Transactional
    public void saveAdmin(RequestAdminSaveAccount requestAdminSaveAccount) {
        User user = mapperAdmin.toModel(requestAdminSaveAccount);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void updateAdmin(RequestAdminUpdateAccount requestUpdateAccountDto, Long id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    user.setLastName(requestUpdateAccountDto.lastName());
                    user.setFirstName(requestUpdateAccountDto.firstName());
                    user.setUsername(requestUpdateAccountDto.username());
                    user.setPassword(passwordEncoder.encode(requestUpdateAccountDto.password()));
                    user.setRoles(mapperListRole.toModel(requestUpdateAccountDto.roles()));
                }, () -> new UsernameNotFoundException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id))
        );
    }


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }


    @Override
    public List<User> findUsersFromOffsetWithLimit(Integer form, Integer count) {
        return userRepository.getUsersFromOffsetWithLimit(form, count);
    }


    @Override
    @Transactional
    public void update(RequestUpdateAccountDto updateAccountDto, Long id) {
        log.info("Начался метод по обноволению данных текущего пользователя");
        log.info("Данные для обновления: {}", updateAccountDto);
        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    user.setLastName(updateAccountDto.lastName());
                    user.setFirstName(updateAccountDto.lastName());
                    user.setPassword(
                            passwordEncoder.encode(updateAccountDto.password())
                    );
                }, () -> new UsernameNotFoundException(ConstantResponseExceptionText.NOT_SUCH_USER)
        );
        log.info("Метод закончился ");
    }

    @Override
    public void deleteById(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id));
        }

    }
}
