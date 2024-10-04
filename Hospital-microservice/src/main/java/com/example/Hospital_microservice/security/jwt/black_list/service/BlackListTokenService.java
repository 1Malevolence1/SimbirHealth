package com.example.Hospital_microservice.security.jwt.black_list.service;


import com.example.Hospital_microservice.security.jwt.black_list.model.BlackListToken;

import java.util.List;

public interface BlackListTokenService {

    void save(BlackListToken token);

    Boolean isTokenBlacklisted(String token);

    List<BlackListToken> findAll();
}
