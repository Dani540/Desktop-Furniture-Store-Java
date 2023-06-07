package com.furniturestore.views;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.dao.UserFormDAO;
import com.others.formsSystem.TextFormatter;
import com.others.formsSystem.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Setter;

public class UserFormView {
    private final UserFormDAO userFormDao;
    @Setter
    private TextField usernameTextField;
    @Setter
    private TextField passwordTextField;
    private String username="", password="";
    private UserType userType;
    private Label feedBackLabel;

    public UserFormView() {
        userFormDao = new UserFormDAO();
    }

    public void onAddButton() {
        if (!username.isBlank()){
            if (!password.isBlank()){
                if (userType!=null) {
                    FurnitureStoreApp.getDataBase().setUserType(userType);
                    userFormDao.setUser(username, password, userType);
                    feedBackLabel.setText("User added success!");
                    cleanForm();
                }else feedBackLabel.setText("Not User Type Selected!");
            }
            else feedBackLabel.setText("Password Empty!");
        }else feedBackLabel.setText("Username Empty!");
    }

    private void cleanForm() {
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    public void initializeTextField(TextField usernameTextField, TextField passwordTextField) {
        new TextFormatter().stringLimitFormatter(12, usernameTextField, passwordTextField);

        usernameTextField.textProperty().addListener( (obs, oldV, newV) ->{
            username = newV;
        });
        passwordTextField.textProperty().addListener((obs, oldV, newV) ->{
            password = newV;
        });
    }

    public void initializeCheckBoxes(CheckBox adminCheckBox, CheckBox vendorCheckBox) {
        adminCheckBox.selectedProperty().addListener( (obs, oldV, newV) ->{
            if (newV) userType = UserType.admin;
            vendorCheckBox.setSelected(oldV);
        });
        vendorCheckBox.selectedProperty().addListener( (obs, oldV, newV) -> {
            if (newV) userType = UserType.vendor;
            adminCheckBox.setSelected(oldV);
        });
    }

    public void setFeedLabel(Label feedBackLabel) {
        this.feedBackLabel = feedBackLabel;
    }
}
