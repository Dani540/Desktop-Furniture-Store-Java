/**
 * Esta clase se usa para asignar formatos de texto a nodos de tipo TextField.
 */

package com.others.formsSystem;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.Arrays;


public class TextFormatter {
    /**
     * Limita el numero de caracteres.
     * @param textField Es el campo al que se le aplica el formato.
     * @param limit Es el limite de caracteres.
     */
    public void stringLimitFormatter(TextField textField, int limit) {
        textField.setTextFormatter(getStringLimitLengthFormatter(limit));
    }

    public void stringLimitFormatter(int limit, TextField ...textFields) {
        Arrays.stream(textFields).forEach( n -> {
            n.setTextFormatter(getStringLimitLengthFormatter(limit));
        });
    }

    /**
     * Para dejar introducir unicamente valores decimales.
     * @param textField Es el campo al que se le aplica el formato.
     */
    public void stringToDoubleFormatter(TextField textField){
        textField.setTextFormatter(getStringToDoubleFormatter());
        textField.setText("");
    }

    /**
     * Para dejar introducir unicamente valores decimales hasta cierto limite.
     * @param textField Es el campo al que se le aplica el formato.
     * @param limit Es el limite de caracteres.
     */
    public void stringToDoubleFormatter(TextField textField, int limit){
        textField.setTextFormatter( getStringToDoubleFormatter() );
        textField.setTextFormatter( getStringLimitLengthFormatter(limit) );
        textField.setText("");

    }

    /**
     * Para dejar introducir unicamente valores enteros.
     * @param textField Es el campo al que se le aplica el formato.
     */
    public void stringToIntFormatter(TextField textField) {
        textField.setTextFormatter(getStringToIntFormatter());
        textField.setText("");
    }

    /**
     * Para dejar introducir unicamente valores enteros hasta cierto limite.
     * @param textField Es el campo al que se le aplica el formato.
     * @param limit Es el limite de caracteres.
     */
    public void stringToIntFormatter(TextField textField, int limit) {
        textField.setTextFormatter(getStringToIntFormatter(limit));
        textField.setText("");
    }

    /**
     * Obtiene un nuevo TextFormatter para Strings de tipo limitador de caracteres.
     * @param limit Es el limite de caracteres.
     * @return Devuelve un TextFormatter con el funcionamiento necesario para limitar los caracteres.
     */
    private javafx.scene.control.TextFormatter<String> getStringLimitLengthFormatter(int limit) {
        return new javafx.scene.control.TextFormatter<>(change ->{
            String newText = change.getControlNewText();
            if (newText.length() <= limit) {
                return change;
            }
            return null;
        });
    }

    /**
     * Obtiene un nuevo TextFormatter para String to Double.
     * El TextFormatter cambia el texto introducido a un double, por lo que solo se aceptan numeros y puntos.
     * @return Devuele un TextFormatter con el funcionamiento necesario para transformar los caracteres en valores double.
     */
    private javafx.scene.control.TextFormatter<Double> getStringToDoubleFormatter() {
        return new javafx.scene.control.TextFormatter<>(new DoubleStringConverter(), 0.0, change -> {
            String aux = change.getControlNewText();
            if (aux.matches("^\\d*(\\.\\d*)?$")) return change;
            return null;
        });
    }

    private javafx.scene.control.TextFormatter<Integer> getStringToIntFormatter() {
        return (new javafx.scene.control.TextFormatter<>(change -> {
            String aux = change.getControlNewText();
            if (aux.matches("\\d*")) return change;
            return null;
        }));
    }

    private javafx.scene.control.TextFormatter<Integer> getStringToIntFormatter(int limit) {
        return (new javafx.scene.control.TextFormatter<>(change -> {
            String aux = change.getControlNewText();
            if (aux.matches("\\d*") && aux.length() <= limit) return change;
            return null;
        }));
    }

    /**
     * Para auto-deslizar la barra vertical en un scrollpane
     * @param scrollPane Es el ScrollPane contenedor.
     * @param vBox Es el VBox con los elementos.
     */

    public void autoScroll(ScrollPane scrollPane, VBox vBox){
        vBox.boundsInLocalProperty().addListener((obs, oldBounds, newBounds) -> {
            double contentHeight = newBounds.getHeight();
            double viewportHeight = scrollPane.getViewportBounds().getHeight();
            double maxVpos = contentHeight - viewportHeight;
            scrollPane.setVvalue(maxVpos);
        });
    }
}
