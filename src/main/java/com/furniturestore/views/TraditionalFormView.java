/**
 * Esta clase maneja todos los elementos visuales y su logica
 * correspondiente de la pantalla "traditionalForm".
 *
 * Hereda de la clase FurnitureForm.
 */

package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.others.formsSystem.FormatterType;
import com.others.formsSystem.FurnitureForm;
import com.others.formsSystem.FurnitureType;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TraditionalFormView extends FurnitureForm {
    /**
     * El constructor establece los elementos del formulario en el arreglo de nodos
     * de su clase padre. Define el tipo de muebles que se va a manejar en el formulario
     * y establece los formateos de texto correspondientes en los campos correspondientes.
     *
     * @param nameTextField Es el TextField para introducir el nombre del mueble.
     * @param descriptionTextArea Es el TextArea para introducir una descripcion.
     * @param weightTextField Es el TextField para introducir el peso del mueble.
     * @param priceTextField Es el TextField para introducir el precio del mueble.
     * @param incompleteDataLabel Es la etiqueta para el manejo de mensajes de error graficos.
     */
    public TraditionalFormView(Label incompleteDataLabel, TextField nameTextField, TextArea descriptionTextArea, TextField weightTextField, TextField priceTextField) {
        super(incompleteDataLabel, nameTextField, descriptionTextArea, weightTextField, priceTextField);

        setTextFormatter(FormatterType.stringToDouble, 9, weightTextField, priceTextField);
        setTextFormatter(FormatterType.stringLimit, 11, nameTextField);
        //setTextFormatter(FormatterType.stringLimit, 9, weightTextField, priceTextField);

        setEnumType(FurnitureType.traditional);
    }

    @Override
    public void addButton() {
        super.addButton();
        cleanForm();
    }

    /**
     * Evento de accion para el boton de volver.
     * Cambia la pantalla a "management"
     */
    public void backButton() {
        SceneController.switchScene(Scenes.management);
    }

    /**
     * Evento de accion para el boton de salir.
     * Invoca una ventana de confirmacion.
     */
    public void exitButton() {
        AuxiliaryWindow.onExitWindow();
    }
}
