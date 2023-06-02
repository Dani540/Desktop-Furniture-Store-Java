/**
 * Este enumerador contiene los tipos de formato disponibles para campos de los formularios.
 */

package com.others.formsSystem;

import javafx.scene.control.TextField;

public enum FormatterType{
    stringLimit(0), stringToDouble(1), stringToInt(2);

    private final TextFormatter textFormatter = new TextFormatter();
    private final int id;

    FormatterType(int id){
        this.id = id;
    }

    /**
     * Aplica un formato de texto en funcion de la constante invocada.
     * @param textField Es el campo al que se le aplicará el formato.
     */
    void setTextFormatter(TextField textField) {
        switch (id){
            case 1 -> textFormatter.stringToDoubleFormatter(textField);
            case 2 -> textFormatter.stringToIntFormatter(textField);
        }
    }
    /**
     * Aplica un formato de texto en funcion de la constante invocada,
     * en este caso solo funciona para el limitador de caracteres.
     * @param textField Es el campo al que se le aplicará el formato.
     * @param limit Es el limite de caracteres.
     */
    void setTextFormatter(TextField textField, int limit){
        if (id==0) textFormatter.formatterLimitLengthText(textField, limit);
    }


}
