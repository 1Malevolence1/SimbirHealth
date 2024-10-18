package com.example.Account_microservice.security.jwt.black_list.service;


import com.example.Account_microservice.security.jwt.black_list.convert.manager_maper.ManagerMapperBlackListToken;
import com.example.Account_microservice.security.jwt.black_list.dto.BlackListTokenDto;
import com.example.Account_microservice.security.jwt.black_list.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class BlackListServiceImpl implements BlackListTokenService {

    private final BlackListRepository blackListRepository;
    private final ManagerMapperBlackListToken managerBlackListToken;


    @Override
    public void save(BlackListTokenDto dto) {
        blackListRepository.save(
                managerBlackListToken.toModel(dto)
        );
        log.info("token: {}, занесён в чёрный списко", dto.token());
    }

    @Override
    public Boolean isTokenBlacklisted(String token) {
        return blackListRepository.existsByToken(token);
    }

}
