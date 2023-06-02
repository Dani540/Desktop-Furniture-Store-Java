package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;

import java.util.List;

public class PersonalizedFurnitureSelectorDAO {
    private final DataBase dataBase;

    public PersonalizedFurnitureSelectorDAO() {
        dataBase = FurnitureStoreApp.getDataBase();
    }
    public List<Furniture> loadPersonalizeds() {
        return dataBase.getFurniture(FurnitureType.personalized);
    }
    public void addToSale(List<Furniture> selectedFurniture) {
        dataBase.addToSale(selectedFurniture);
    }

    public void addToSale(Furniture furniture) {
        dataBase.addToSale(furniture);
    }
}
