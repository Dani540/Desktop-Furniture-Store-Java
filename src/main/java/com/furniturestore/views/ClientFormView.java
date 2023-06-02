package com.furniturestore.views;

import com.others.formsSystem.ClientForm;
import com.others.formsSystem.FormatterType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientFormView extends ClientForm {

    /**
     * El constructor define los formatos de texto de cada campo del formulario.
     * @param incompleteData Es la etiqueta para el manejo de errores graficos.
     * @param nodes Son los campos en los que se introduciran los datos.
     */
    public ClientFormView(Label incompleteData, Node... nodes) {
        super(incompleteData, nodes);

        setTextFormatter(FormatterType.stringLimit, 11, (TextField) nodes[1], (TextField) nodes[2]);
        setTextFormatter(FormatterType.stringLimit, 8, (TextField) nodes[0]);
        setTextFormatter(FormatterType.stringLimit, 3, (TextField) nodes[3]);
        setTextFormatter(FormatterType.stringToInt, (TextField) nodes[0], (TextField) nodes[3]);
    }

    /**
     * Evento de accion para el boton de confimar cliente.
     */
    public void onConfirmButton() {
        addButton();
    }
}
