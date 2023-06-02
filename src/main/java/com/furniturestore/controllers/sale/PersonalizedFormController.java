package com.furniturestore.controllers.sale;

import com.furniturestore.views.PersonalizedFormView;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PersonalizedFormController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private CheckBox requireBuildCheckBox;
    @FXML
    private Label incompleteDataLabel;
    private PersonalizedFormView personalizedFormView;

    public void initialize(){
        personalizedFormView = new PersonalizedFormView(incompleteDataLabel, nameTextField, descriptionTextArea, weightTextField, priceTextField, requireBuildCheckBox);
    }

    public void onAddButton() {
        personalizedFormView.onAddButton();
    }

    public void onBackButton() {
        personalizedFormView.onBackButton();
    }

    public void onExitButton() {
        personalizedFormView.onExitButton();
    }
}
