package com.example.Account_microservice.user.service.guest_user;

import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;
import com.example.Account_microservice.user.model.User;

public interface GuestUserService {

    User addAccount(RequestSingInGuestUserDto dto);


}
