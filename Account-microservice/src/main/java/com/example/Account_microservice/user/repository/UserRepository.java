package com.example.Account_microservice.user.repository;

import com.example.Account_microservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    @Query(value = "SELECT * FROM users ORDER BY user_id LIMIT :count OFFSET :from", nativeQuery = true)
    List<User> getUsersFromOffsetWithLimit(@Param("from") Integer from, @Param("count") Integer count);


}
