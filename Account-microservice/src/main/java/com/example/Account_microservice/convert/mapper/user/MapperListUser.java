package com.example.Account_microservice.convert.mapper.user;


import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUser.class)
public interface MapperListUser {

    List<ResponseUserAccountDto> toDTO(List<User> listModel);

}
