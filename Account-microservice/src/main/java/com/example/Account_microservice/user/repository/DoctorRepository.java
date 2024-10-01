package com.example.Account_microservice.user.repository;

import com.example.Account_microservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<User, Long> {


    // TODO Перерделать запрос
    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_roles ur ON u.user_id = ur.user_id " +
            "WHERE ur.role_id = 3 AND u.username LIKE %:filterName% " +
            "ORDER BY u.user_id LIMIT :count OFFSET :from",
            nativeQuery = true)
    List<User> findAllDoctors(@Param("filterName") String filterName, @Param("from") Integer from, @Param("count") Integer count);

}
