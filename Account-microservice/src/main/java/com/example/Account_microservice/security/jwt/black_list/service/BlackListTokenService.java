package com.example.Account_microservice.security.jwt.black_list.service;

import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;

public interface BlackListTokenService {

    Boolean isTokenBlacklisted(String token);

    void save(BlackListTokenDto dto);
}
