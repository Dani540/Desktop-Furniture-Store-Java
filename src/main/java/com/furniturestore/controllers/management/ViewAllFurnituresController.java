/**
 * Controlador de la pantalla "viewAllFurnitures"
 */

package com.furniturestore.controllers.management;

import com.furniturestore.views.ViewAllFurnituresView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ViewAllFurnituresController {
    @FXML
    private VBox infoVBox;
    @FXML
    private VBox listVBox;
    private final ViewAllFurnituresView viewAllFurnituresView;

    public ViewAllFurnituresController() {
        viewAllFurnituresView = new ViewAllFurnituresView();
    }

    /**
     * El inicializador carga los contenedores de las listas y la lista con sus botones.
     */
    public void initialize(){
        viewAllFurnituresView.setListVBox(listVBox);
        viewAllFurnituresView.setInfoVBox(infoVBox);
        viewAllFurnituresView.loadList();
    }

    /**
     * Evento de accion para el boton volver
     */
    @FXML
    private void onBackButton() {
        viewAllFurnituresView.onBackButton();
    }

    /**
     * Evento de accion para el boton salir
     */
    @FXML
    private void onExitButton() {
        viewAllFurnituresView.onExitButton();
    }

    /**
     * Evento de accion para el boton de remover.
     */
    @FXML
    private void onRemoveButton() {
        viewAllFurnituresView.onRemoveButton();
    }
}
