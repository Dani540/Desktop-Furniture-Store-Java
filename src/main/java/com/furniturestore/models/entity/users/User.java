package com.furniturestore.models.entity.users;

import com.furniturestore.models.entity.Entity;
import com.others.formsSystem.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public final class User extends Entity {
    private int id;
    private final String username;
    private final String password;
    private final UserType userType;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getData(){
        return username+password;
    }
}
