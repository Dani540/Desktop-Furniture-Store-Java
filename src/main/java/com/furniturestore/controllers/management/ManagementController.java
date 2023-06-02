/**
 * Controlador para la pantalla "managementView".
 */

package com.furniturestore.controllers.management;

import com.furniturestore.views.ManagementView;
import javafx.fxml.FXML;

public class ManagementController {
    private final ManagementView managementView;

    /**
     * El construcor inicializa el objeto managementView
     */
    public ManagementController() {
        managementView = new ManagementView();
    }

    /**
     * Evento de accion para el boton volver.
     * Usa la instancia de managementView.
     */
    @FXML
    private void onBackButton() {
        managementView.backButton();
    }

    /**
     * Evento de accion para el boton de añadir tradicional.
     * Usa la instancia de managementView.
     */
    @FXML
    private void onAddTradButton() {
        managementView.onAddTradButton();
    }

    /**
     * Evento de accion para el boton ver todos los muebles.
     * Usa la instancia de managementView.
     */
    @FXML
    private void onViewAllButton() {
        managementView.onViewAllButton();
    }

    /**
     * Evento de accion para el boton de ver muebles tradicionales.
     */
    public void onViewTradButton() {
        managementView.onViewTradButton();
    }

    /**
     * Evento de accion para el boton de ver muebles personalizados.
     */
    public void onViewPerButton() {
        managementView.onViewPerButton();
    }

    /**
     * Evento de accion para el boton de restablecer datos.
     */
    public void onResetDataButton() {
        managementView.onResetDataButton();
    }
}
