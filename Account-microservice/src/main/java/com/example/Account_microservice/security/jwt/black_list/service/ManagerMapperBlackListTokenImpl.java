package com.example.Account_microservice.security.jwt.black_list.service;

import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.model.BlackListToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerMapperBlackListTokenImpl implements ManagerMapperBlackListToken {
    private final MapperBlackListToken mapperBlackListToken;
    @Override
    public BlackListToken toModel(BlackListTokenDto dto) {
        return mapperBlackListToken.toModel(dto);
    }
}
