package com.example.Account_microservice.user.service.user;

import com.example.Account_microservice.user.model.Role;
import com.example.Account_microservice.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    User save(User user);

    User findUserByUsername(String username);

    User findUserById(Long id);

    Optional<User> findUserByIdReturnOptional(Long id);

    void update(User user, Long id);

    void deleteById(Long id);

    List<User> getUsersFromOffsetWithLimit(Integer from, Integer count);
    List<User> getUsersFromOffset(Integer from);
    List<User> findAll();


    List<String> getRolesUserById(Long id);
}
