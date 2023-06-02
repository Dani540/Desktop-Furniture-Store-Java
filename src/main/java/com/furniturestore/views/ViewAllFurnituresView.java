package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.ViewFurnitureDAO;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import com.others.furnitureSystem.ViewFurniture;

public class ViewAllFurnituresView extends ViewFurniture {
    public ViewAllFurnituresView() {
        super( new ViewFurnitureDAO() );
    }

    public void onBackButton() {
        SceneController.switchScene(Scenes.management);
    }

    public void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }

    public void onRemoveButton() {
        super.onRemoveButton();
    }
}
