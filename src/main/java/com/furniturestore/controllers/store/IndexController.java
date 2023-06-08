/**
 * Controlador para la pantalla "index".
 */

package com.furniturestore.controllers.store;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.views.IndexView;
import com.repository.DataBase;
import javafx.fxml.FXML;

public class IndexController{
    private final IndexView indexView;

    public IndexController() {
        indexView = new IndexView();
    }

    public void initialize(){
        DataBase.getInstance().getRepository().clearSale();
    }

    /**
     * Metodo de evento para el boton de "newSale".
     * Se invoca al metodo "onSaleButton" del objeto "indexView".
     */
    @FXML
    private void onSaleButton(){
        indexView.onSaleButton();
    }
    /**
     * Metodo de evento para el boton de "management".
     * Se invoca al metodo "onManagementButton" del objeto "indexView".
     */
    @FXML
    private void onManagementButton(){
        indexView.onManagementButton();
    }
    /**
     * Metodo de evento para el boton de "informationButton".
     * Se invoca al metodo "onInformationButton" del objeto "indexView".
     */
    @FXML
    private void onInformationButton(){
        indexView.onInformationView();
    }
    /**
     * Metodo de evento para el boton de "exitButton".
     * Se invoca al metodo "onExitButton" del objeto "indexView".
     */
    @FXML
    private void onExitButton(){
        indexView.onExitButton();
    }

}
