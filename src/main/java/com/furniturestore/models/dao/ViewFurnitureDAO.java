package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.others.formsSystem.FurnitureType;

public class ViewFurnitureDAO {
    public void removeFurniture(int idSelected, FurnitureType typeSelected) {
        if (idSelected > 0 && typeSelected!=null) FurnitureStoreApp.getDataBase().removeFurniture(idSelected, typeSelected);
    }
}
