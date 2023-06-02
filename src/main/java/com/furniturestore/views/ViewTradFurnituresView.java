package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.ViewFurnitureDAO;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.formsSystem.FurnitureType;
import com.others.sceneSystem.Scenes;
import com.others.furnitureSystem.ViewFurniture;

public class ViewTradFurnituresView extends ViewFurniture {
    public ViewTradFurnituresView() {
        super(new ViewFurnitureDAO());
        setTypeSelected(FurnitureType.traditional);
    }

    public void onBackButton() {
        SceneController.switchScene(Scenes.management);
    }

    public void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }
}
