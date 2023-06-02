package com.furniturestore.controllers.sale;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.views.TicketView;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TicketViewController {
    @FXML
    private VBox ticketClientContainerVboxLeft;
    @FXML
    private VBox ticketClientContainerVboxRight;
    @FXML
    private Label dateLabel;
    @FXML
    private HBox ticketDataQualitiesContainerHBox;
    private final TicketView ticketView;

    public TicketViewController() {
        ticketView = new TicketView();
    }

    public void initialize(){
        ticketView.loadDate(dateLabel);
        ticketView.loadClient(ticketClientContainerVboxLeft, ticketClientContainerVboxRight);
        ticketView.loadSale(ticketDataQualitiesContainerHBox);
    }

    @FXML
    private void onPrintButton() {
        System.out.println("Print...");
    }

    @FXML
    private void onAcceptButton() {
        SceneController.switchScene(Scenes.index);
    }

    @FXML
    private void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }
}
