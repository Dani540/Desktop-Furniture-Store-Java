package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.repository.DataBase;

import java.util.List;

public class NewSaleDAO {
    public List<Furniture> loadSale() {
        return DataBase.getInstance().getRepository().getSale();
    }
}
