package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.repository.DataBase;
import com.repository.Repository;

public class ResetDataDAO {

    private final Repository repository = DataBase.getInstance().getRepository();

    public void removeAll() {
        repository.removeAllFurniture();
    }

    public void removeTrad() {
        repository.removeTradFurniture();
    }

    public void removePer() {
        repository.removePerFurniture();
    }
}
