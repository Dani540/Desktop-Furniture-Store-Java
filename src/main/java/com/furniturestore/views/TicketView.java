package com.furniturestore.views;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.dao.TicketDAO;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.RClient;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class TicketView {
    private final TicketDAO ticketDAO;

    public TicketView() {
        ticketDAO = new TicketDAO();
    }

    public void loadDate(Label dateLabel) {
        dateLabel.setText(ticketDAO.getDate());
    }

    public void loadClient(VBox ticketClientContainerVboxLeft, VBox ticketClientContainerVboxRight){
        RClient rClient = FurnitureStoreApp.getRClient();
        ticketClientContainerVboxLeft.getChildren().add(new Label("Rut: "));
        ticketClientContainerVboxRight.getChildren().add(new Label(rClient.getRut()));
        if (!rClient.getName().isEmpty()) {
            ticketClientContainerVboxLeft.getChildren().add(new Label("Name: "));
            ticketClientContainerVboxRight.getChildren().add(new Label(rClient.getName()));
        }
        if (!rClient.getLastname().isEmpty()) {
            ticketClientContainerVboxLeft.getChildren().add(new Label("Lastname: "));
            ticketClientContainerVboxRight.getChildren().add(new Label(rClient.getLastname()));
        }
    }

    public void loadSale(HBox ticketDataQualitiesContainerHBox) {
        List<VBox> vBoxes = ticketDataQualitiesContainerHBox.getChildren().stream().map(n -> (VBox) n).toList();
        List<Furniture> furnitures = ticketDAO.loadSale();

        furnitures.forEach(f ->{
            vBoxes.forEach(n ->{
                switch (n.getId()){
                    case "ticketDataIdVBox" ->    n.getChildren().add(new Label(String.valueOf(f.getId())));
                    case "ticketDataNameVBox" ->  n.getChildren().add(new Label(f.getName()));
                    case "ticketDataTypeVBox" ->  n.getChildren().add(new Label( ticketDAO.getFurnitureType(f).name() ));
                    case "ticketDataPriceVBox" -> n.getChildren().add(new Label(String.valueOf(f.getPrice())));
                }
            });
        });
    }
}
