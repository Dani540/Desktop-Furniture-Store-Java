/**
 * Controlador de la pantalla "traditionalForm".
 */

package com.furniturestore.controllers.management;

import com.furniturestore.views.TraditionalFormView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TraditionalFormController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private Label incompleteDataLabel;
    private TraditionalFormView traditionalFormView;

    /**
     * Inicializa la instancia "traditionalFormView" para
     * la gestion de los elementos graficos y su logica.
     */
    public void initialize() {
        traditionalFormView = new TraditionalFormView(incompleteDataLabel, nameTextField, descriptionTextArea, weightTextField, priceTextField);
    }
    /**
     * Evento de accion para el boton de añadir.
     * Usa la instancia de traditionalFormView
     */
    @FXML
    private void onAddButton() {
        traditionalFormView.addButton();
    }
    /**
     * Evento de accion para el boton de volver.
     * Usa la instancia de traditionalFormView
     */
    @FXML
    private void onBackButton() {
        traditionalFormView.backButton();
    }
    /**
     * Evento de accion para el boton de salir.
     * Usa la instancia de traditionalFormView
     */
    @FXML
    private void onExitButton() {
        traditionalFormView.exitButton();
    }
}
