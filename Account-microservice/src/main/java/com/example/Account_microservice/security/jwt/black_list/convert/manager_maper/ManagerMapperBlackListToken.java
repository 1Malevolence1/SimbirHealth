package com.example.Account_microservice.security.jwt.black_list.convert.manager_maper;

import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.model.BlackListToken;

public interface ManagerMapperBlackListToken {
    BlackListToken toModel(BlackListTokenDto dto);
}