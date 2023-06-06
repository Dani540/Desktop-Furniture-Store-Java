package com.others.sceneSystem;

import com.furniturestore.FurnitureStoreApp;

import java.net.URL;

public enum Scenes{
    login(0),
    index(1),
    newSale(2),
    management(3),
    information(4),
    traditionalForm(5),
    traditionalSelector(6),
    viewAllFurniture(7),
    viewTradFurniture(8),
    viewPerFurniture(9),
    personalizedForm(10),
    resetData(11),
    clientForm(12),
    ticketView(13),
    userForm(14);

    private URL resource;
    private String style;

    Scenes(int id){
        setURI(id);
        setStyle(id);
    }

    private void setURI(int id) {
        String path = "views/";
        switch (id){
            case 0 -> path += ("login.fxml");
            case 1 -> path += ("index.fxml");
            case 2 -> path += ("newSale.fxml");
            case 3 -> path += ("management.fxml");
            case 4 -> path += ("information.fxml");
            case 5 -> path += ("traditionalForm.fxml");
            case 6 -> path += ("traditionalSelector.fxml");
            case 7 -> path += ("viewAllFurniture.fxml");
            case 8 -> path += ("viewTradFurniture.fxml");
            case 9 -> path += ("viewPerFurniture.fxml");
            case 10 -> path += ("personalizedForm.fxml");
            case 11 -> path += ("resetData.fxml");
            case 12 -> path += ("clientForm.fxml");
            case 13 -> path += ("ticketView.fxml");
            case 14 -> path += ("userForm.fxml");
            default -> path += ("default.fxml");
        }
        resource = getResourcePath(path);
    }

    private void setStyle(int id) {
        String path = "views/css/";
        switch (id){
            case 0 -> path += "login.css";
            case 1 -> path += "index.css";
            case 2 -> path += "new-sale.css";
            case 3 -> path += "management.css";
            case 4 -> path += "information.css";
            case 5, 10 -> path += "forms.css";
            case 6 -> path += "traditional-selector.css";
            case 7,8,9 -> path += "view-furniture.css";
            case 11 -> path += "reset-data.css";
            case 12 -> path += "client-form.css";
            case 13 -> path += "ticket.css";
            case 14 -> path += "user-form.css";
            default -> path+= "default.css";
        }
        style = getResourcePath(path).toString();
    }

    public URL getResource(){
        return resource;
    }

    public String getStyle() {
        return style;
    }

    private URL getResourcePath(String path){
        return FurnitureStoreApp.class.getResource(path);
    }

}
