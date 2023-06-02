package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.others.sceneSystem.Scenes;

public class ManagementView {
    public void backButton() {
        SceneController.switchScene(Scenes.index);
    }

    public void onAddTradButton() {
        SceneController.switchScene(Scenes.traditionalForm);
    }

    public void onViewAllButton() {
        SceneController.switchScene(Scenes.viewAllFurniture);
    }

    public void onResetDataButton() {
        SceneController.switchScene(Scenes.resetData);
    }

    public void onViewTradButton() {
        SceneController.switchScene(Scenes.viewTradFurniture);
    }

    public void onViewPerButton() {
        SceneController.switchScene(Scenes.viewPerFurniture);
    }
}
