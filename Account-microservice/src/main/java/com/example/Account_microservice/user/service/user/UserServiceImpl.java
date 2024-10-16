package com.example.Account_microservice.user.service.user;


import com.example.Account_microservice.config.ConstantResponseExceptionText;
import com.example.Account_microservice.exception.Validate;
import com.example.Account_microservice.user.model.User;
import com.example.Account_microservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDeleted(false);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new UsernameAlreadyExistsException(new Validate(ConstantResponseExceptionText.USERNAME_ALREADY_EXISTS));
        }
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
    @Transactional
    public void update(User updateUser, Long id) {
        try {

            userRepository.findById(id).ifPresentOrElse(

                    user -> {
                        if (updateUser.getUsername() != null) user.setUsername(updateUser.getUsername());
                        if (updateUser.getLastName() != null) user.setLastName(updateUser.getLastName());
                        if (updateUser.getFirstName() != null) user.setFirstName(updateUser.getFirstName());
                        if (updateUser.getPassword() != null)
                            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                        if (updateUser.getRoles() != null) {
                            userRepository.deleteAllRolesForUser(id);
                            user.setRoles(updateUser.getRoles());
                        }
                    }, () -> {
                        throw new UsernameNotFoundException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id));
                    }
            );
        } catch (DataIntegrityViolationException exception) {
            throw new UsernameAlreadyExistsException(new Validate(ConstantResponseExceptionText.USERNAME_ALREADY_EXISTS));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public List<User> getUsersFromOffsetWithLimit(Integer from, Integer count) {
        return userRepository.getUsersFromOffsetWithLimit(from, count);
    }


    @Override
    public List<User> getUsersFromOffset(Integer form) {
        return userRepository.getUsersFromOffset(form);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getRolesUserById(Long id) {
        if (!userRepository.existsById(id))
            throw new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_USER_BY_ID.formatted(id));
        return userRepository.getRolesById(id);
    }

}
