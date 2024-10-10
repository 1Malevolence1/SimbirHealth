package com.example.Timetable_microservice.security.jwt.black_list.repository;


import com.example.Timetable_microservice.security.jwt.black_list.model.BlackListToken;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BlackListRepository extends JpaRepository<BlackListToken, Long> {

    Boolean existsByToken(String token);
}