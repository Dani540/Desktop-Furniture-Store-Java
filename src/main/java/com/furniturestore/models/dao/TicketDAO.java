package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.TraditionalFurniture;
import com.others.formsSystem.FurnitureType;

import java.util.List;

public class TicketDAO {
    public List<Furniture> loadSale() {
        return FurnitureStoreApp.getDataBase().getSale();
    }

    public FurnitureType getFurnitureType(Furniture furniture) {
        return furniture instanceof TraditionalFurniture ? FurnitureType.traditional : FurnitureType.personalized;
    }

    public String getDate() {
        return FurnitureStoreApp.getDataBase().getDate();
    }
}
