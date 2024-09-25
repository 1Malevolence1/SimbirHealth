package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.user.convert.UserMapper;
import com.example.Account_microservice.user.dto.RequestSingUpUserDto;
import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User save(RequestSingUpUserDto dto) {
        User user = ConvertUser.toModel(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
