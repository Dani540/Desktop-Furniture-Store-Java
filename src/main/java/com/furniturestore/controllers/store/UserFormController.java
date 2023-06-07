package com.furniturestore.controllers.store;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.views.UserFormView;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserFormController {
    @FXML
    private Label feedbackLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private CheckBox adminCheckBox;
    @FXML
    private CheckBox vendorCheckBox;
    private final UserFormView userFormView;

    public UserFormController() {
        userFormView = new UserFormView();
    }

    public void initialize(){
        userFormView.initializeTextField(usernameTextField, passwordTextField);
        userFormView.initializeCheckBoxes(adminCheckBox, vendorCheckBox);
        userFormView.setFeedLabel(feedbackLabel);
        userFormView.setUsernameTextField(usernameTextField);
        userFormView.setPasswordTextField(passwordTextField);
    }

    @FXML
    private void onAddButton() {
        userFormView.onAddButton();
    }

    @FXML
    private void onBackButton() {
        SceneController.switchScene(Scenes.login);
    }

    @FXML
    private void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }
}
