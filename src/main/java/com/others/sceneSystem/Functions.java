package com.others.sceneSystem;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.controllers.SceneController;

public enum Functions{
    closeApp(0), confirmSale(1);

    IFunction IFunction;

    Functions(int id){
        setFunction(id);
    }

    private void setFunction(int id) {
        switch (id){
            case 0 -> IFunction = () -> FurnitureStoreApp.getStage().close();
            case 1 -> IFunction = () -> SceneController.switchScene(Scenes.ticketView);
        }
    }

}
