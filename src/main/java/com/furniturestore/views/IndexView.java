/**
 * Esta clase sirve para controlar la logica
 * de los elementos graficos
 */

package com.furniturestore.views;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.controllers.SceneController;
import com.others.formsSystem.UserType;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;

public class IndexView {
    /**
     * Cambia a la pantalla "newSale"
     */
    public void onSaleButton() {
        SceneController.switchScene(Scenes.newSale);
    }

    /**
     * Cambia a la escena "management"
     */
    public void onManagementButton() {
        if (!FurnitureStoreApp.getUserType().equals(UserType.vendor)) SceneController.switchScene(Scenes.management);
    }

    /**
     * Cambia a la escena "information"
     */
    public void onInformationView() {
        SceneController.switchScene(Scenes.information);
    }

    /**
     * Despliega una ventana para cerrar el programa
     */
    public void onExitButton(){
        AuxiliaryWindow.onExitWindow();
    }
}
