/**
 * Esta interface crea un contrato para los formularios que la implementen.
 */

package com.others.formsSystem;

import javafx.scene.Node;

import java.util.List;

public interface IForm {
    Node[] getData();
    void addEntity(Node[] data);
    int getEntityId();
    boolean allFieldValid();
    List<Boolean> validateFields(Node ...fields);
    boolean isValidField(Node field);
    String getNoFieldValidString(int id);
    String generateIncompleteText(List<Boolean> fieldValids);
    void cleanForm();
}
