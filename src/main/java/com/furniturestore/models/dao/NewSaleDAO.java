package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;

import java.util.List;

public class NewSaleDAO {
    public List<Furniture> loadSale() {
        return FurnitureStoreApp.getDataBase().getSale();
    }
}
