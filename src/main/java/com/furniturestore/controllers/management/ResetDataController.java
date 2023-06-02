/**
 * Controlador para la vista de restablecer datos.
 */

package com.furniturestore.controllers.management;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.ResetDataDAO;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;

public class ResetDataController {

    private final ResetDataDAO resetDataDAO;

    public ResetDataController() {
        resetDataDAO = new ResetDataDAO();
    }

    /**
     * Evento de accion para el boton restablecer todos los muebles.
     */
    public void onRemoveAllButton() {
        resetDataDAO.removeAll();
    }

    /**
     * Evento de accion para el boton de restablecer muebles tradicionales.
     */
    public void onRemoveTradButton() {
        resetDataDAO.removeTrad();
    }

    /**
     * Evento de accion para el boton de restablecer muebles personalizados.
     */
    public void onRemovePerButton() {
        resetDataDAO.removePer();
    }

    /**
     * Evento de accion para el boton de volver.
     */
    public void onBackButton() {
        SceneController.switchScene(Scenes.management);
    }

    /**
     * Evento de accion para el boton de salir.
     */
    public void onExitButton( ) {
        AuxiliaryWindow.onExitWindow();
    }
}
