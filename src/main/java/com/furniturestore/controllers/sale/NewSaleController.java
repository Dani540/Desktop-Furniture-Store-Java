/**
 * Controlador para la pantalla "new sale"
 */

package com.furniturestore.controllers.sale;

import com.furniturestore.views.NewSaleView;
import com.others.formsSystem.TextFormatter;
import com.others.sceneSystem.AuxiliaryWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class NewSaleController {
    @FXML
    private ScrollPane scrollPaneTicket;
    @FXML
    private VBox leftVBoxTicket;
    @FXML
    private VBox rightVBoxTicket;
    private final NewSaleView newSaleView;
    private final TextFormatter textFormatter;

    /**
     * En el controlador instanciamos un objeto a nivel de clase
     * de la clase NewSaleView para la gestion de elementos graficos
     * de la pantalla.
     */
    public NewSaleController() {
        newSaleView = new NewSaleView();
        textFormatter = new TextFormatter();
    }

    /**
     * Este metodo inicializa la pantalla cargando la lista de los elementos
     * en el carro de compra y configurando este para que se deslice verticalmente
     * de manera automatica.
     */
    public void initialize(){
        newSaleView.loadTicket(leftVBoxTicket, rightVBoxTicket);
        textFormatter.autoScroll(scrollPaneTicket, leftVBoxTicket);
        textFormatter.autoScroll(scrollPaneTicket, rightVBoxTicket);
    }

    /**
     * Este metodo crea una ventana auxiliar
     * para salir del programa
     */
    @FXML
    private void exitButton() {
        AuxiliaryWindow.onExitWindow();
    }

    /**
     * Este metodo es el gestor de eventos para el boton " back"
     * Uso el objeto de NewSaleView para manejar su logica.
     */
    @FXML
    private void onBackButton() {
        newSaleView.onBackButton();
    }

    /**
     * Este metodo es el gestor de evento del boton "Traditional"
     * Uso el objeto de NewSaleView para manejar su logica.
     */
    public void traditionalSaleButton() {
        newSaleView.traditionalSaleButton();
    }

    /**
     * Este metodo es el gestor de eventos para el boton "Personalized"
     * Uso el objeto de NewsSaleView para manejar su logica.
     */
    public void personalizedSaleButton() {
        newSaleView.onPersonalizedSaleButton();
    }

    /**
     * Evento de accion para el boton de pagar.
     */

    public void onPayButton() {
        newSaleView.onPayButton();
    }
}
