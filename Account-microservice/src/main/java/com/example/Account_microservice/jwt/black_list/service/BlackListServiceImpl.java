package com.example.Account_microservice.jwt.black_list.service;


import com.example.Account_microservice.jwt.black_list.model.BlackListToken;
import com.example.Account_microservice.jwt.black_list.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BlackListServiceImpl implements BlackListTokenService {

    private final BlackListRepository blackListRepository;

    @Override
    public void save(BlackListToken token) {
         blackListRepository.save(token);
    }

    @Override
    public Boolean isTokenBlacklisted(String token) {
        return blackListRepository.existsByToken(token);
    }

    @Override
    public List<BlackListToken> findAll() {
        return blackListRepository.findAll();
    }
}
