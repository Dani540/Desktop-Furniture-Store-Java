package com.furniturestore;

import com.furniturestore.controllers.SceneController;
import com.others.RClient;
import com.others.formsSystem.UserType;
import com.repository.DataBase;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

import static com.others.sceneSystem.Scenes.login;

public class FurnitureStoreApp extends Application {
    @Getter
    private static Stage stage;
    @Getter
    private static DataBase dataBase;
    @Setter @Getter
    private static UserType userType;
    @Setter @Getter
    private static String username;
    @Setter @Getter
    public static RClient rClient;
    @Override
    public void start(Stage stage){
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        FurnitureStoreApp.stage = stage;
        dataBase = new DataBase();
        SceneController.switchScene(login);

    }

    public static void main(String[] args) {
        launch();
    }
}