    package com.example.Account_microservice.user.convert.mapper.user;


    import com.example.Account_microservice.user.convert.mapper.role.MapperListRole;
    import com.example.Account_microservice.user.dto.RequestSingUpUserAccountDto;
    import com.example.Account_microservice.user.dto.RequestUpdateUserAccountDto;
    import com.example.Account_microservice.user.dto.ResponseUserAccountDto;
    import com.example.Account_microservice.user.dto.admin.RequestAdminSaveAccount;
    import com.example.Account_microservice.user.dto.admin.RequestAdminUpdateAccount;
    import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
    import com.example.Account_microservice.user.model.User;
    import org.mapstruct.Mapper;

    @Mapper(componentModel = "spring", uses = MapperListRole.class)
    public interface MapperUser {

        ResponseUserAccountDto toDTO(User user);

        User toModelFromSingUP(RequestSingUpUserAccountDto dto);

        User toModelFromSingUpGuestUser(RequestSingInGuestUserDto dto);

        User toModelFromUpdateAccount(RequestUpdateUserAccountDto dto);

        User toModelFromAdminUpdate(RequestAdminUpdateAccount dto);

        User toModelFromAdminSave(RequestAdminSaveAccount dto);


    }
