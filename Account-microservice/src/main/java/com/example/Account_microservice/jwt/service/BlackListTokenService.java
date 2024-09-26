package com.example.Account_microservice.jwt.service;

import com.example.Account_microservice.jwt.model.BlackListToken;

import java.util.List;

public interface BlackListTokenService {

    void save(BlackListToken token);

    Boolean isTokenBlacklisted(String token);

    List<BlackListToken> findAll();
}
