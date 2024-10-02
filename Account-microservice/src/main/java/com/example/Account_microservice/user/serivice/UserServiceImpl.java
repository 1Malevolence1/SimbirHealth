package com.example.Account_microservice.user.serivice;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.convert.manager_mapper.ManagerMapperAccount;
import com.example.Account_microservice.user.dto.RequestSingUpAccountDto;
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

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerMapperAccount managerMapperAccount;


    @Override
    @Transactional
    public User save(RequestSingUpAccountDto dto) {
        User user = managerMapperAccount.toModelFromSignUp(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.builder().id(1L).roleName("ROLE_USER").build()));
        return userRepository.save(user);

    }


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_USERNAME.formatted(username)));

    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id)));
    }

    @Override
    public Optional<User> findUserByIdReturnOptional(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User updateUser, Long id) {

        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    if(updateUser.getUsername() != null) user.setUsername(updateUser.getUsername());
                    if(updateUser.getLastName() != null) user.setLastName(updateUser.getLastName());
                    if(updateUser.getFirstName() != null) user.setFirstName(updateUser.getFirstName());
                    if(updateUser.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));
                    if(updateUser.getRoles() != null) user.setRoles(updateUser.getRoles());
                }, () -> new UsernameNotFoundException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id))
        );
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
