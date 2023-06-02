package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.repository.DataBase;

public class ResetDataDAO {

    private final DataBase dataBase = FurnitureStoreApp.getDataBase();

    public void removeAll() {
        dataBase.removeAllFurniture();
    }

    public void removeTrad() {
        dataBase.removeTradFurniture();
    }

    public void removePer() {
        dataBase.removePerFurniture();
    }
}
