/**
 * Controlador para la pantalla "traditionalSelector"
 */

package com.furniturestore.controllers.sale;

import com.furniturestore.views.TraditionalSelectorView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TraditionalSelectorController {
    @FXML
    private VBox leftVBox;
    @FXML
    private VBox rightVBox;
    @FXML
    private VBox leftCheckBoxVBox;
    private TraditionalSelectorView traditionalSelectorView;

    /**
     * Este metodo se llama al iniciar la pantalla (por configuracion del framework JavaFX)
     * Y crea una nueva instancia de la clase TraditionalSelectorView, con los parametros de los
     * nodos visuales que gestionaremos en esta clase.
     */
    public void initialize(){
        traditionalSelectorView = new TraditionalSelectorView(leftVBox, rightVBox, leftCheckBoxVBox);
        traditionalSelectorView.loadList();
    }

    /**
     * Este metodo es el evento de accion del boton "exit" de la pantalla.
     * Utiliza la instancia de traditionalSelectorView.
     */
    @FXML
    private void onExitButton() {
        traditionalSelectorView.onExitButton();
    }
    /**
     * Evento de accion para el boton confirmar.
     * Usa la instancia de traditionalSelectorView.
     */
    public void onConfirmButton() {
        traditionalSelectorView.onConfirmButton();
    }
    /**
     * Evento de accion para el boton cancelar.
     * Usa la instancia de traditionalSelectorView.
     */
    public void onCancelButton() {
        traditionalSelectorView.onCancelButton();
    }
}
