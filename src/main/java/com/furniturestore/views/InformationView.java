package com.furniturestore.views;

import com.furniturestore.models.dao.InformationDAO;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class InformationView {

    // User: username
    // Range: range of user
    // Traditional furniture registered:
    // Personalized furniture registered:
    // Date: xx-xx-23

    private final InformationDAO informationDAO;

    public InformationView() {
        informationDAO = new InformationDAO();
    }

    public void loadData(VBox namesVBox, VBox valueVBox){
        Map<String, String> information = informationDAO.getInfo();
        information.keySet().forEach(n -> namesVBox.getChildren().add(new Label(" "+ n +" ")));
        information.values().forEach(n -> valueVBox.getChildren().add(new Label(" "+ n + " ")));
    }
}
