package com.furniturestore.controllers.management;

import com.furniturestore.views.ViewPerFurnitureView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ViewPerFurnituresController {

    @FXML
    private VBox listVBox;
    @FXML
    private VBox infoVBox;
    private final ViewPerFurnitureView viewPerFurnitureView;

    public ViewPerFurnituresController() {
        viewPerFurnitureView = new ViewPerFurnitureView();
    }
    public void initialize(){
        viewPerFurnitureView.setListVBox(listVBox);
        viewPerFurnitureView.setInfoVBox(infoVBox);
        viewPerFurnitureView.loadList();
    }

    public void onRemoveButton() {
        viewPerFurnitureView.onRemoveButton();
    }

    public void onBackButton() {
        viewPerFurnitureView.onBackButton();
    }

    public void onExitButton() {
        viewPerFurnitureView.onExitButton();
    }
}
