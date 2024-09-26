package com.example.Account_microservice.jwt.repository;

import com.example.Account_microservice.jwt.model.BlackListToken;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BlackListRepository extends JpaRepository<BlackListToken, Long> {

    Boolean existsByToken(String token);
}
