package com.furniturestore.controllers.sale;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.views.ClientFormView;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientFormController {
    @FXML
    private ClientFormView clientFormView;
    @FXML
    private TextField rutTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private Label incompleteDataLabel;

    public void initialize() {
        clientFormView = new ClientFormView(incompleteDataLabel, rutTextField, nameTextField, lastNameTextField, ageTextField);
    }

    @FXML
    private void onConfirmButton() {
        clientFormView.onConfirmButton();
    }
    @FXML
    private void onCancelButton() {
        SceneController.switchScene(Scenes.index);
    }

    @FXML
    private void onExitButton(){
        AuxiliaryWindow.onExitWindow();
    }
}
