package com.example.Account_microservice.user.service.guest_user;

import com.example.Account_microservice.user.dto.guest.RequestSingInGuestUserDto;

public interface GuestUserService {

    void addAccount(RequestSingInGuestUserDto dto);
}
