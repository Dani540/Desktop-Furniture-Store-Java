package com.furniturestore.controllers.management;

import com.furniturestore.views.ViewTradFurnituresView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ViewTradFurnituresController {
    @FXML
    private VBox listVBox;
    @FXML
    private VBox infoVBox;
    private final ViewTradFurnituresView viewTradFurnituresView;

    public ViewTradFurnituresController() {
        viewTradFurnituresView = new ViewTradFurnituresView();
    }

    public void initialize(){
        viewTradFurnituresView.setListVBox(listVBox);
        viewTradFurnituresView.setInfoVBox(infoVBox);
        viewTradFurnituresView.loadList();
    }

    @FXML
    private void onRemoveButton() {
        viewTradFurnituresView.onRemoveButton();
    }
    @FXML
    private void onBackButton() {
        viewTradFurnituresView.onBackButton();
    }
    @FXML
    private void onExitButton() {
        viewTradFurnituresView.onExitButton();
    }
}
