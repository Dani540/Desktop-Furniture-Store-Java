package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;

public class ViewFurnitureDAO {
    public void removeFurniture(int idSelected, FurnitureType typeSelected) {
        if (idSelected > 0 && typeSelected!=null) DataBase.getInstance().getRepository().removeFurniture(idSelected, typeSelected);
    }
}
