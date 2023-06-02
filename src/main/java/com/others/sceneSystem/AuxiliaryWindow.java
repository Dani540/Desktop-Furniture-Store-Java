package com.others.sceneSystem;

import com.furniturestore.FurnitureStoreApp;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.StageStyle;

import java.util.Objects;
import java.util.Optional;

import static com.furniturestore.controllers.SceneController.switchScene;

public abstract class AuxiliaryWindow implements IFunction {
     /**
     * ESte metodo genera una ventana auxiliar de tipo confirmacion
     * para cerrar el programa.
     */

     public static void onExitWindow(){
         onConfirmWindow("Confirmation", "Exit", "Are you sure", Functions.closeApp);
     }

     public static void onConfirmSale(){
         onConfirmWindow("Confirmation", "New Sale", "Confirm Sale?", Functions.confirmSale);
     }

    public static void onConfirmWindow(String title, String header, String content, Functions function){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().getStyleClass().add("custom-alert");
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(FurnitureStoreApp.class.getResource("views/css/custom-alert.css")).toString());
        alert.setGraphic(null);
        alert.initStyle(StageStyle.TRANSPARENT);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals( ButtonType.OK)) function.IFunction.execute();
    }

    public static void minimizeScreen(Scene scene){
    }

    public static void refreshScreen(Scene scene, Scenes sceneID) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.R)) switchScene(sceneID);
        });
    }

}
