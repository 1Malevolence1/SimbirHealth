package com.example.Account_microservice.user.service.user;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    @Transactional
    public User save(User user) {
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
    public void update(User updateUser, Long id) {

        userRepository.findById(id).ifPresentOrElse(
                user -> {
                    if (updateUser.getUsername() != null) user.setUsername(updateUser.getUsername());
                    if (updateUser.getLastName() != null) user.setLastName(updateUser.getLastName());
                    if (updateUser.getFirstName() != null) user.setFirstName(updateUser.getFirstName());
                    if (updateUser.getPassword() != null) user.setPassword(user.getPassword());
                    if (updateUser.getRoles() != null) user.setRoles(updateUser.getRoles());
                }, () -> {
                  throw  new UsernameNotFoundException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id));
                }
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

    @Override
    public List<User> getUsersFromOffsetWithLimit(Integer from, Integer count) {
        return userRepository.getUsersFromOffsetWithLimit(from, count);
    }
}
