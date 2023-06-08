package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;
import com.repository.Repository;

import java.util.List;

public class PersonalizedFurnitureSelectorDAO {
    private final Repository repository;

    public PersonalizedFurnitureSelectorDAO() {
        repository = DataBase.getInstance().getRepository();
    }
    public List<Furniture> loadPersonalizeds() {
        return repository.getFurniture(FurnitureType.personalized);
    }
    public void addToSale(List<Furniture> selectedFurniture) {
        repository.addToSale(selectedFurniture);
    }

    public void addToSale(Furniture furniture) {
        repository.addToSale(furniture);
    }
}
