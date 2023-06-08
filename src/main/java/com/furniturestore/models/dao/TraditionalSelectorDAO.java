package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;
import com.repository.Repository;

import java.util.List;

public class TraditionalSelectorDAO {
    private final Repository repository;

    public TraditionalSelectorDAO() {
        repository = DataBase.getInstance().getRepository();
    }

    public List<Furniture> loadTraditionals() {
        return repository.getFurniture(FurnitureType.traditional);
    }

    public void addToSale(List<Furniture> selectedFurniture) {
        repository.addToSale(selectedFurniture);
    }
}
