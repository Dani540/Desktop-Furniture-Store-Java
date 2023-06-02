package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;

import java.util.HashMap;
import java.util.Map;

public class InformationDAO {
    public Map<String, String> getInfo() {

        Map<String, String> info = new HashMap<>();
        DataBase dataBase = FurnitureStoreApp.getDataBase();

        info.put("User", FurnitureStoreApp.getUsername());
        info.put("Range", FurnitureStoreApp.getUserType().name());
        info.put("Traditional furniture registered", String.valueOf(dataBase.getFurnitureQuatity(FurnitureType.traditional)));
        info.put("Personalized furniture registered", String.valueOf(dataBase.getFurnitureQuatity(FurnitureType.personalized)));
        info.put("Date", dataBase.getDate());

        return info;
    }
}
