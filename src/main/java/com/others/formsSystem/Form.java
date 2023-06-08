package com.others.formsSystem;

import com.furniturestore.models.dao.DAO;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Setter
public abstract class Form implements IForm {
    @Getter
    private DAO dao;
    @Getter
    private Label incompleteLabel;
    private EnumType enumType;
    private Node[] data;

    /**
     * Evento de accion para el boton de añadir de los formularios.
     */
    public abstract void addButton();

    /**
     * Este metodo verifica todos los campos del formulario.
     * Muestra mensajes de error por pantalla
     * @return Devuelve true o false en funcion de si estan o no completados.
     */
    @Override
    public boolean allFieldValid() {
        Node[] data = getData();
        List<Boolean> fieldValids = validateFields(data);
        return (fieldValids.stream().allMatch(Boolean::booleanValue));
    }

    /**
     * Este metodo asigna el mensaje de error en relacion a todos los campos del formulario.
     */
    public void setIncompleteDataText(){
        incompleteLabel.setText(generateIncompleteText(validateFields(data)));
    }

    /**
     * Este metodo asigna un mensaje de error personalizado.
     * @param message Es el mensaje a mostrar.
     */
    public void setIncompleteDataText(String message){
        incompleteLabel.setText(message);
    }

    /**
     * Muestra el mensaje de error en relacion a los campos requeridos.
     * @param nodes Son los campos requeridos.
     */
    public void setIncompleteDataText(Node ...nodes){
        incompleteLabel.setText(generateIncompleteText(validateFields(nodes)));
    }

    /**
     * Muestra el mensaje de error en relacion si los campos especificados son validos o no. (vacios)
     * @param validFields Es la lista de booleanos con los campos validos en orden.
     */
    public void setIncompleteDataText(List<Boolean> validFields){
        incompleteLabel.setText(generateIncompleteText(validFields));
    }

    /**
     * Este metodo obtiene los datos del mueble ( Node[] ) y los devuelve, en caso de no haber
     * sido declarados se devuelve un arreglo vacio.
     * @return Devuelve un arreglo de nodos con los datos del mueble.
     */
    @Override
    public Node[] getData() {
        return data==null ? new Node[]{} : data;
    }
    /**
     * Añade una entidad de mueble instanciada (con
     * los datos antes establecidos y verificados) a la base de datos
     * mediante un DataObject instanciado a nivel de clase.
     *
     * El tipo de mueble se establece mediante otra variable de clase,
     * si esta es nula no se añade nada.
     *
     * @param data Son los datos del mueble.
     */
    @Override
    public void addEntity(Node[] data) {
        if (enumType != null) dao.addEntity(enumType.getInstance(getEntityId(), data));
        else System.out.println("EnumType equals null");
    }

    /**
     * Obtiene un ID valido para la entidad generada, segun su tipo.
     * Utiliza la instancia del DataObject para esto.
     * @return devuelve un ID valido.
     */
    @Override
    public int getEntityId() {
        return dao.getId(enumType);
    }

    /**
     * Valida los campos del formulario.
     * @param fields Son los campos de los datos.
     * @return Devuelve una lista de booleanos, donde cada indice es el campo correspondiente.
     * (Para el primer elemento es el primer campo del formulario, etc...)
     */
    @Override
    public List<Boolean> validateFields(Node... fields) {
        return Arrays.stream(fields).map(this::isValidField).toList();
    }

    /**
     * Valida un campo de texto (considerando TextField y TextArea) en funcion de si está vacio y si contiene mas de un punto (invalido).
     * @param field Es el campo para validar.
     * @return Devuelve true o false en funcion de si no esta vacio y solo tiene un punto.
     */
    @Override
    public boolean isValidField(Node field) {
        boolean validField;
        if (field instanceof TextField aux){
            validField = !aux.getText().isEmpty();
        }else if (field instanceof TextArea aux){
            validField = !aux.getText().isEmpty();
        }else validField = true;
        return validField;
    }

    /**
     * Obtiene el texto para campo no valido segun su id.
     * @param id Es el id del campo
     * @return Devuele un String con el texto que se defina dentro del metodo.
     */
    @Override
    public abstract String getNoFieldValidString(int id);

    /**
     * Genera el texto de los campos no validos.
     * @param fieldValids Es la lista de booleanos con los datos de los campos validos.
     * @return Devuelve una cadena con el mensaje de error
     */
    @Override
    public String generateIncompleteText(List<Boolean> fieldValids) {
        List<String> list = IntStream.range(0, fieldValids.size())
                .filter(i -> !fieldValids.get(i))
                .mapToObj(this::getNoFieldValidString)
                .toList();
        return String.join(", ",list) + "\nEmpty!";
    }

    /**
     * Limpia los datos introducios en el formulario y
     * los deja con su promp text.
     */
    @Override
    public void cleanForm() {
        Arrays.stream(data).forEach(n -> {
            if (n instanceof TextField aux){
                aux.setText("");
                aux.setPromptText(aux.getPromptText());
            } else if (n instanceof TextArea aux) {
                aux.setText("");
                aux.setPromptText(aux.getPromptText());
            } else if (n instanceof CheckBox aux) {
                aux.setSelected(false);
            }
        });
    }

    /**
     * Asigna formato de texto a un TextField.
     * @param formatterType Es el formato de texto.
     * @param textField Es el textfield a formatear.
     */
    public void setTextFormatter(FormatterType formatterType, TextField textField){
        formatterType.setTextFormatter(textField);
    }

    /**
     * Asigna formato de texto a varios TextField's.
     * @param formatterType Es el formato de texto.
     * @param textField Son los textfield a formatear.
     */
    public void setTextFormatter(FormatterType formatterType, TextField ... textField){
        for (TextField txtF : textField){
            formatterType.setTextFormatter(txtF);
        }
    }

    /**
     * Asigna formato de texto a un TextField.
     * @param formatterType Es el formato de texto.
     * @param limit Es el limite de caracteres.
     * @param textField Es el textfield a formatear.
     */
    public void setTextFormatter(FormatterType formatterType, int limit, TextField textField){
        formatterType.setTextFormatter(textField, limit);
    }

    /**
     * Asigna formato de texto a varios TextField's.
     * @param formatterType Es el formato de texto.
     * @param limit Es el limite de caracteres.
     * @param textField Son los textfield a formatear.
     */
    public void setTextFormatter(FormatterType formatterType, int limit, TextField ... textField){
        for (TextField txtF : textField){
            formatterType.setTextFormatter(txtF, limit);
        }
    }
}
