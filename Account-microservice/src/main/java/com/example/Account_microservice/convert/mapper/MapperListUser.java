package com.example.Account_microservice.convert.mapper;


import com.example.Account_microservice.user.dto.ResponseAccountDto;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUser.class)
public interface MapperListUser {

    List<ResponseAccountDto> toDTO(List<User> listModel);

}
