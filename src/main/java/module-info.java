module com.furniturestore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires lombok;
    requires java.sql;


    opens com.furniturestore to javafx.fxml, lombok;
    exports com.furniturestore;

    opens com.furniturestore.controllers to javafx.fxml, lombok;
    exports com.furniturestore.controllers;

    opens com.services to javafx.fxml, org.controlsfx.controls, lombok;
    exports com.services;

    opens com.customerrors to javafx.fxml, lombok;
    exports com.customerrors;

    opens com.repository to javafx.fxml, lombok;
    exports com.repository;
    exports com.furniturestore.views;

    opens com.furniturestore.views to javafx.fxml, lombok;
    exports com.others.formsSystem;

    opens com.others.formsSystem to javafx.fxml, lombok;
    exports com.furniturestore.models.dao;

    opens com.furniturestore.models.dao to javafx.fxml, lombok;
    exports com.others.furnitureSystem;

    opens com.others.furnitureSystem to javafx.fxml, lombok;
    exports com.others.sceneSystem;

    opens com.others.sceneSystem to javafx.fxml, lombok;
    exports com.furniturestore.controllers.sale;

    opens com.furniturestore.controllers.sale to javafx.fxml, lombok;
    exports com.furniturestore.controllers.management;

    opens com.furniturestore.controllers.management to javafx.fxml, lombok;
    exports com.furniturestore.controllers.store;

    opens com.furniturestore.controllers.store to javafx.fxml, lombok;

    opens com.furniturestore.models.entity.users to javafx.fxml, lombok;
    exports com.furniturestore.models.entity.users;

    opens com.furniturestore.models.entity.furniture to javafx.fxml, lombok;
    exports com.furniturestore.models.entity.furniture;
}