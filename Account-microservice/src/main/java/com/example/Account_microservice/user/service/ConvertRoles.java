package com.example.Account_microservice.user.service;


import com.example.Account_microservice.user.model.Role;

import java.util.ArrayList;
import java.util.List;

public final class ConvertRoles {

    public static List<Role> toModel(List<String> listDto){
        if(listDto == null) return null;

        List<Role> listModel = new ArrayList<>();
        for(String stringRole : listDto){
            listModel.add(Role.builder().roleName(stringRole).build());
        }

        return listModel;
    }
}
