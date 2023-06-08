package com.services;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;
import com.repository.Repository;
import com.repository.Table;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

public class TicketService{
    private final DataBase dataBase;
    private final Table ticketTable;
    public TicketService(DataBase dataBase) {
        this.dataBase = dataBase;
        ticketTable = new Table("tickets", List.of("clientRut", "traditionalAmount", "personalizedAmount", "total"));
    }

    @SneakyThrows
    public void addTicket(List<FurnitureType> typesOfFurnitures, List<Furniture> furnitures) {
        PreparedStatement preparedStatement = dataBase.getCONNECTION().prepareStatement(insertInto(ticketTable.table(), ticketTable.values()));
        preparedStatement.setString(1, FurnitureStoreApp.getRClient().getRut());
        preparedStatement.setInt(2, (int) typesOfFurnitures.stream().filter(n -> n.getId() == FurnitureType.traditional.getId()).toList().size());
        preparedStatement.setInt(3, (int) typesOfFurnitures.stream().filter(n-> n.getId() == FurnitureType.personalized.getId()).toList().size());
        preparedStatement.setDouble(4, furnitures.stream().mapToDouble(Furniture::getPrice).sum());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * Este metodo reduce la escritura de codigo repetitivo para los "insert"
     * en la base de datos.
     * @param table El nombre de la tabla.
     * @param values Los valores de la tabla.
     * @return Devuele la sentencia SQL para la insercion de datos.
     */
    private String insertInto(String table, List<String> values){
        return "INSERT INTO " + table + " (" + String.join(",",values) + ") VALUES (" + String.join(",", Collections.nCopies(values.size(), "?")) + ")";
    }
}

