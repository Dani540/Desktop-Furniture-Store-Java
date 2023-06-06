/**
 * Esta clase implementa la interface IForm y se usa
 * para los formularios de muebles.
 */

package com.others.formsSystem;

import com.furniturestore.models.dao.FurnitureFormDAO;
import javafx.scene.Node;
import javafx.scene.control.Label;


public class FurnitureForm extends Form {

    public FurnitureForm() {
        setDao(new FurnitureFormDAO());
    }
    /**
     * El constructor establece los elementos del formulario en el arreglo de nodos
     * de su clase padre. Define el tipo de muebles que se va a manejar en el formulario
     * y establece los formateos de texto correspondientes en los campos correspondientes.
     *
     * @param incompleteDataLabel Es la etiqueta para el manejo de mensajes de error graficos.
     * @param nodes Son los nodes para manejar el formulario.
     */
    public FurnitureForm(Label incompleteDataLabel, Node ... nodes) {
        setIncompleteLabel(incompleteDataLabel);
        setData(nodes);
        setDao(new FurnitureFormDAO());
    }

    /**
     * Evento de accion para el boton de añadir de los
     * formularios de muebles.
     */
    @Override
    public void addButton() {
        if (allFieldValid()) addEntity(getData());
        else setIncompleteDataText();
    }

    /**
     * Devuelve la cadena del mensaje de error grafico para la
     * gestion de campos vacios.
     * @param id Es el campo vacio.
     * @return Devuelve la cadena correspondiente.
     */
    @Override
    public String getNoFieldValidString(int id) {
        String statement = "";
        switch (id){
            case 0 -> statement = "Name";
            case 1 -> statement = "Description";
            case 2 -> statement = "Weight";
            case 3 -> statement = "Price";
            default -> statement = "All";
        }
        return statement;
    }
}
