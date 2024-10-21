package com.example.Account_microservice.user.convert.mapper.user;


import com.example.Account_microservice.user.convert.mapper.role.MapperListRole;
import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
import com.example.Account_microservice.user.dto.admin.ResponseUserAccountDtoForAdmin;
import com.example.Account_microservice.user.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapperUser.class, MapperListRole.class})
public interface MapperListUser {

    List<ResponseUserAccountDto> toDTO(List<User> listModel);
    List<ResponseUserAccountDtoForAdmin> toDtoForAdmin(List<User> listModel);

}
