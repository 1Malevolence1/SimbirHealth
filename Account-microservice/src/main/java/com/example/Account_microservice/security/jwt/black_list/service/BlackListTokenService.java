package com.example.Account_microservice.security.jwt.black_list.service;

import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.model.BlackListToken;

import java.util.List;

public interface BlackListTokenService {

    void save(BlackListToken token);

    Boolean isTokenBlacklisted(String token);

    List<BlackListToken> findAll();

    void save(BlackListTokenDto dto);
}
