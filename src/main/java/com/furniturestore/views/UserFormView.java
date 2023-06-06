package com.furniturestore.views;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.dao.UserFormDAO;
import com.others.formsSystem.TextFormatter;
import com.others.formsSystem.UserType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserFormView {
    private final UserFormDAO userFormDao;
    private String username="", password="";
    private UserType userType;
    private Label test;

    public UserFormView() {
        userFormDao = new UserFormDAO();
    }

    public void onAddButton() {
        if (!username.isBlank()){
            if (!password.isBlank()){
                if (userType!=null) {
                    FurnitureStoreApp.getDataBase().setUserType(userType);
                    userFormDao.setUser(username, password, userType);
                    test.setText("User added success!");
                }else test.setText("Not User Type Selected!");
            }
            else test.setText("Password Empty!");
        }else test.setText("Username Empty!");
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

    public void setTest(Label test) {
        this.test = test;
    }
}
