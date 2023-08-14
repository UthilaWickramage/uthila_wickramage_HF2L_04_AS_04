package com.jiat.crud_application.services;

import com.jiat.crud_application.model.UserDetails;

public class UserService {
    public UserDetails getUserByName(String name){
        return new UserDetails("kamal","1234");
    }
}
