/**
 * Esta clase se usa para asignar formatos de texto a nodos de tipo TextField.
 */

package com.others.formsSystem;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class TextFormatter {
    /**
     * Limita el numero de caracteres.
     * @param textField Es el campo al que se le aplica el formato.
     * @param limit Es el limite de caracteres.
     */
    public void formatterLimitLengthText(TextField textField, int limit) {
        textField.textProperty().addListener( (observable, oldV, newV) ->{
            if (newV.length() > limit) textField.setText(oldV);
        } );
    }

    /**
     * Para dejar introducir unicamente valores decimales.
     * @param textField Es el campo al que se le aplica el formato.
     */
    public void stringToDoubleFormatter(TextField textField){
        textField.setTextFormatter(new javafx.scene.control.TextFormatter<>(new DoubleStringConverter(), 0.0, change -> {
            String aux = change.getControlNewText();
            if (aux.matches("\\d*(\\.\\d*)?")) return change;
            return null;
        }));
        textField.setText("");
    }

    /**
     * Para dejar introducir unicamente valores enteros.
     * @param textField Es el campo al que se le aplica el formato.
     */
    public void stringToIntFormatter(TextField textField) {
        textField.setTextFormatter(new javafx.scene.control.TextFormatter<>(new IntegerStringConverter(), 0, change -> {
            String aux = change.getControlNewText();
            if (aux.matches("\\d*")) return change;
            return null;
        }));
        textField.setText("");
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
