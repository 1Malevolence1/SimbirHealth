package com.example.Account_microservice.security.jwt.black_list.service;

import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.model.BlackListToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperBlackListToken {
    BlackListToken toModel(BlackListTokenDto dto);
}
