/**
 * Clase encargada del manejo de elementos visuales
 * en la pantalla "personalizedForm"
 */
package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.PersonalizedFurnitureSelectorDAO;
import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.PersonalizedFurniture;
import com.others.formsSystem.FormatterType;
import com.others.formsSystem.FurnitureForm;
import com.others.formsSystem.FurnitureType;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PersonalizedFormView extends FurnitureForm {
    public PersonalizedFormView(Label incompleteDataLabel, TextField nameTextField, TextArea descriptionTextArea, TextField weightTextField, TextField priceTextField, CheckBox requireBuildCheckBox) {
        super(incompleteDataLabel, nameTextField, descriptionTextArea, weightTextField, priceTextField, requireBuildCheckBox);

        setEnumType(FurnitureType.personalized);

        setTextFormatter(FormatterType.stringToDouble, 9, weightTextField, priceTextField);
        setTextFormatter(FormatterType.stringLimit, 12, nameTextField);
    }

    public void onAddButton() {
        if (allFieldValid()) {
            addButton();
            addToSale();
            cleanForm();
            SceneController.switchScene(Scenes.newSale);
        }else setIncompleteDataText();
    }

    public void addToSale(){
        new PersonalizedFurnitureSelectorDAO().addToSale(getEntity());
    }
    private Furniture getEntity() {
        int id = getEntityId();
        String name = ( (TextField) getData()[0]).getText();
        String description = ( (TextArea) getData()[1]).getText();
        double weight = Double.parseDouble(( (TextField) getData()[2]).getText());
        double price = Double.parseDouble(( (TextField) getData()[3]).getText());
        boolean requireBuild = ( (CheckBox) getData()[4]).isSelected();
        return allFieldValid() ? new PersonalizedFurniture(id, name, description, weight,price, requireBuild) : null;
    }

    /**
     * Este metodo es el evento del boton de volver.
     * Cambia la pantalla a "newSale"
     */
    public void onBackButton() {
        SceneController.switchScene(Scenes.newSale);
    }

    /**
     * Este metodo es el evento del boton de salir.
     * Invoca un cuadro de confirmacion para esto.
     */
    public void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }
}
