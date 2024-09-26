package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
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
    public User save(RequestSingUpAccountDto dto) {
         User user = ConvertUser.toModel(dto);
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Set.of(Role.builder().id(1L).roleName("ROLE_USER").build()));
         return userRepository.save(user);

    }




    @Override
    public User findUser(String username) {
        return  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
