package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;
import com.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class InformationDAO {
    public Map<String, String> getInfo() {

        Map<String, String> info = new HashMap<>();
        Repository repository = DataBase.getInstance().getRepository();

        info.put("User", FurnitureStoreApp.getUsername().substring(1));
        info.put("Range", FurnitureStoreApp.getUserType().name());
        info.put("Traditional furniture registered", String.valueOf(repository.getFurnitureQuantity(FurnitureType.traditional)));
        info.put("Personalized furniture registered", String.valueOf(repository.getFurnitureQuantity(FurnitureType.personalized)));
        info.put("Date", repository.getDate());

        return info;
    }
}
