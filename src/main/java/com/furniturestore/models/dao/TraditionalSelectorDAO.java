package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;

import java.util.List;

public class TraditionalSelectorDAO {
    private final DataBase dataBase;

    public TraditionalSelectorDAO() {
        dataBase = FurnitureStoreApp.getDataBase();
    }

    public List<Furniture> loadTraditionals() {
        return dataBase.getFurniture(FurnitureType.traditional);
    }

    public void addToSale(List<Furniture> selectedFurniture) {
        dataBase.addToSale(selectedFurniture);
    }
}
