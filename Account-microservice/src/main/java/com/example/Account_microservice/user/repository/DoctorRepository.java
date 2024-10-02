package com.example.Account_microservice.user.repository;

import com.example.Account_microservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_roles ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE r.role_id = 3 AND u.username LIKE %:filterName% " +
            "ORDER BY u.user_id LIMIT :count OFFSET :from",
            nativeQuery = true)
    List<User> findAllDoctors(@Param("filterName") String filterName, @Param("from") Integer from, @Param("count") Integer count);


    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_roles ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE r.role_id = 3 AND u.username LIKE %:filterName% " +
            "ORDER BY u.user_id OFFSET :from",
            nativeQuery = true)
    List<User> findAllDoctorsWithoutCount(@Param("filterName") String filterName, @Param("from") Integer from);



    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_roles ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE r.role_id = 3 ORDER BY u.user_id LIMIT :count OFFSET :from",
            nativeQuery = true)
    List<User> findAllDoctorsWithoutNameFilter(@Param("from") Integer from, @Param("count") Integer count);

    @Query(value = "SELECT u.* FROM users u " +
            "JOIN user_roles ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE r.role_id = 3 AND u.user_id = :id",
            nativeQuery = true)
    Optional<User> findDoctorByIdReturnOption(@Param("id") Long id);


}

