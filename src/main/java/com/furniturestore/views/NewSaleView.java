package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.NewSaleDAO;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.sceneSystem.Scenes;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class NewSaleView {

    private final NewSaleDAO newSaleDAO;

    public NewSaleView() {
        newSaleDAO = new NewSaleDAO();
    }

    /**
     * Este metodo cambia la pantalla a la de "index".
     */
    public void onBackButton() {
        SceneController.switchScene(Scenes.index);
    }

    /**
     * Este metodo cambia la pantalla a la de "traditionalSelector"
     */
    public void traditionalSaleButton() {
        SceneController.switchScene(Scenes.traditionalSelector);
    }

    /**
     * Este metodo carga el carro de compra con los elementos correspondientes.
     * @param leftVBoxTicket Es el contenedor para los nombres de los elementos.
     * @param rightVBoxTicket Es el contenedor para los precios de los elementos.
     */
    public void loadTicket(VBox leftVBoxTicket, VBox rightVBoxTicket) {
        List<Furniture> sale = newSaleDAO.loadSale();
        ObservableList<Node> productNames = leftVBoxTicket.getChildren();
        ObservableList<Node> productPrices = rightVBoxTicket.getChildren();
        sale.forEach(n ->{
            productNames.add(new Label(" " + n.getName() + " "));
            productPrices.add(new Label(" " + n.getPrice() + " "));
        });
    }

    public void onPersonalizedSaleButton() {
        SceneController.switchScene(Scenes.personalizedForm);
    }

    public void onPayButton() {
        if (newSaleDAO.loadSale().size()>0) SceneController.switchScene(Scenes.clientForm);
        else System.out.println("NO SUFFICIENT AMOUNT");
    }
}
