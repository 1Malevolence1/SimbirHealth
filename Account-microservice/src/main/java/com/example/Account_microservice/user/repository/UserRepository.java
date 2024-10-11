package com.example.Account_microservice.user.repository;

import com.example.Account_microservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    @Query(value = "SELECT * FROM users WHERE deleted = false ORDER BY user_id LIMIT :count OFFSET :from ", nativeQuery = true)
    List<User> getUsersFromOffsetWithLimit(@Param("from") Integer from, @Param("count") Integer count);


    @Query(value = "SELECT * FROM users WHERE deleted = false ORDER BY user_id OFFSET :from ", nativeQuery = true)
    List<User> getUsersFromOffset(@Param("from") Integer from);


    @Modifying
    @Query(value = "DELETE FROM user_roles ur WHERE ur.user_id = :id", nativeQuery = true)
    void deleteAllRolesForUser(@Param("id") Long id);

    @Query(value = "SELECT r.role_name role_name FROM users AS us JOIN user_roles AS ur USING(user_id) JOIN role as r USING(role_id) WHERE us.user_id = :id",nativeQuery = true)
    List<String> getRolesById(@Param("id") Long id);

}
