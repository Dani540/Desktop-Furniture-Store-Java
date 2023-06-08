package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.TraditionalFurniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;

import java.util.List;

public class TicketDAO {
    public List<Furniture> loadSale() {
        return DataBase.getInstance().getRepository().getSale();
    }

    public FurnitureType getFurnitureType(Furniture furniture) {
        return furniture instanceof TraditionalFurniture ? FurnitureType.traditional : FurnitureType.personalized;
    }

    public String getDate() {
        return DataBase.getInstance().getRepository().getDate();
    }

    public void addTicket() {
        List<Furniture> furnitures = loadSale();
        List<FurnitureType> typesOfFurnitures = furnitures.stream().map(this::getFurnitureType).toList();
        DataBase.getInstance().getRepository().addTicket(typesOfFurnitures, furnitures);
    }
}
