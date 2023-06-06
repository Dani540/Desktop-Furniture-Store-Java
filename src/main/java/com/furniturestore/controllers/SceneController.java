/**
 * Esta clase funciona como controlador para el
 * cambio entre pantallas, es abstracta al contener
 * solo un metodo estatico para su posterior uso.
 */

package com.furniturestore.controllers;

import com.furniturestore.FurnitureStoreApp;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.sceneSystem.Scenes;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Objects;

public abstract class SceneController {

    @Setter @Getter
    private static Stage stage;

    /**
     * Metodo para cambiar la pantalla actual.
     * Al tener constantes con los nombres de las pantallas,
     * guardo en estos tambien su ruta "fxml" para su pantalla y su estilo propio (en css).
     * @param sceneID La constante de la pantalla a la que nos dirigiremos.
     */
    @SneakyThrows
    public static void switchScene(Scenes sceneID) {
        FXMLLoader fxmlLoader = new FXMLLoader(sceneID.getResource());
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(FurnitureStoreApp.class.getResource("views/css/default.css")).toString());
        scene.getStylesheets().add(Objects.requireNonNull(FurnitureStoreApp.class.getResource("views/css/effects.css")).toString());
        scene.getStylesheets().add(sceneID.getStyle());
        scene.fillProperty().set(Color.TRANSPARENT);

        AuxiliaryWindow.refreshScreen(scene, sceneID);
        AuxiliaryWindow.minimizeScreen(scene);
        stage.setScene(scene);
        stage.show();
    }
}
