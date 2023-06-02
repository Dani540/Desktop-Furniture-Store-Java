package com.furniturestore.controllers.store;

import com.furniturestore.controllers.SceneController;
import com.others.sceneSystem.Scenes;

public class DefaultController {
    public void onBackButton() {
        SceneController.switchScene(Scenes.index);
    }
}
