package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.config.ConstantResponseText;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.dto.RequestUpdateAccountDto;
import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User save(RequestSingUpAccountDto dto) {
         User user = ConvertUser.toModel(dto);
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Set.of(Role.builder().id(1L).roleName("ROLE_USER").build()));
         return userRepository.save(user);

    }




    @Override
    public User findUserByUsername(String username) {
        return  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Override
    public User findUserById(Long id) {
        return  userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }



    @Override
    @Transactional
    public void update(RequestUpdateAccountDto updateAccountDto, Long id) {
            log.info("Начался метод по обноволению данных текущего пользователя");
            log.info("Данные для обновления: {}", updateAccountDto);
            userRepository.findById(id).ifPresentOrElse(
                    user ->{
                        user.setLastName(updateAccountDto.lastName());
                        user.setFirstName(updateAccountDto.lastName());
                        user.setPassword(
                                passwordEncoder.encode(updateAccountDto.password())
                        );
                    } , () -> new UsernameNotFoundException(ConstantResponseText.NOT_SUCH_USER)
            );
            log.info("Метод закончился ");
    }
}
