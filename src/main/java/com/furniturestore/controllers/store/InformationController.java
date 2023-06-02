package com.furniturestore.controllers.store;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.views.InformationView;
import com.others.sceneSystem.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class InformationController {
    private final InformationView informationView;
    @FXML
    private VBox namesContainerVBox;
    @FXML
    private VBox valueContainerVBox;

    public InformationController() {
        informationView = new InformationView();
    }

    public void initialize(){
        informationView.loadData(namesContainerVBox, valueContainerVBox);
    }

    @FXML
    private void onBackButton() {
        SceneController.switchScene(Scenes.index);
    }
}
