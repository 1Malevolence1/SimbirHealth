    package com.example.Account_microservice.convert.mapper;


    import com.example.Account_microservice.user.dto.*;
    import com.example.Account_microservice.user.model.User;
    import org.mapstruct.Mapper;

    @Mapper(componentModel = "spring", uses = MapperListRole.class)
    public interface MapperUser {

        ResponseAccountDto toDTO(User user);

        User toModelFromSingUP(RequestSingUpAccountDto dto);

        User toModelFromUpdateAccount(RequestUpdateAccountDto dto);

        User toModelFromAdminUpdate(RequestAdminUpdateAccount dto);

        User toModelFromAdminSave(RequestAdminSaveAccount dto);


    }
