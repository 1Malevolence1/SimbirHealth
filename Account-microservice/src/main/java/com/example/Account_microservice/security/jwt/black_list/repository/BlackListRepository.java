package com.example.Account_microservice.security.jwt.black_list.repository;

import com.example.Account_microservice.security.jwt.black_list.model.BlackListToken;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BlackListRepository extends JpaRepository<BlackListToken, Long> {

    Boolean existsByToken(String token);
}
