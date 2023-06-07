package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.UserType;

public class UserFormDAO {
    public void setUser(String username, String password, UserType userType) {
        User user = new User(username, password, userType);
        FurnitureStoreApp.getDataBase().addUser(user);
    }
}
