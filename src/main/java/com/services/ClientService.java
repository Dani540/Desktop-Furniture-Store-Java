/**
 * Esta clase contiene todos los servicios requeridos para el manejo de datos
 * en la base de datos de los clientes.
 */

package com.services;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.users.Client;
import com.others.formsSystem.UserType;
import com.repository.DataBase;
import com.repository.Repository;
import com.repository.Table;
import lombok.Data;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientService implements IService<Client, UserType> {

    private final DataBase dataBase;

    public ClientService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Esta implementacion de addEntity añade una entidad de cliente
     * a la tabla respectiva en la base de datos.
     * @param client Es el cliente ingresado.
     */
    @SneakyThrows
    @Override
    public void addEntity(Client client) {
        Connection connection = dataBase.getCONNECTION();
        Table userTable = UserType.client.getTable();
        String statement = insertInto(userTable.table(), userTable.values());
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, client.getRut());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getLastName());
        preparedStatement.setInt(4, client.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * Esta implementacion del metodo getEntities lee todos los clientes registrados en la
     * base de datos y devuelve una lista con las instancias de estos.
     * @return devuelve una lista de instancias de Client.
     */
    @SneakyThrows
    @Override
    public List<Client> getEntities() {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();
        return getClients(statement);
    }

    /**
     * Un-use
     */
    @SneakyThrows
    @Override
    public List<Client> getEntities(UserType type) {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();

        return getClients(statement);
    }

    /**
     * Obtiene los clientes en su respectiva tabla en la base de datos.
     * @param statement Es la sentencia SQL;
     * @return Devuelve una lista con las instancias de Client con los datos antes obtenidos.
     */
    @SneakyThrows
    private List<Client> getClients(Statement statement) {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + UserType.client.getTable().table());
        List<Client> list = new ArrayList<>();

        while(resultSet.next()){
            int rut = resultSet.getInt("rut");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");
            int age = resultSet.getInt("age");

            list.add( new Client(rut, name, lastname, age) );
        }
        resultSet.close();
        return list;
    }

    /**
     * Metodo auxiliar para la simplificacion de codigo.
     * @param table Es la tabla para la insercion de datos.
     * @param values Son los valores para insertar.
     * @return Devuelve la sentencia SQL requerida para insertar datos en la tabla correspondiente.
     */
    private String insertInto(String table, List<String> values){
        return "INSERT INTO " + table + " (" + String.join(",",values) + ") VALUES (" + String.join(",", Collections.nCopies(values.size(), "?")) + ")";
    }

    /**
     * Obtiene la informacion de un cliente.
     * @param client Es el cliente a leer.
     * @return Devuelve una lista con la informacion del cliente.
     */
    @SneakyThrows
    public List<String> getInfo(Client client){
        List<String> infoList = new ArrayList<>();

        String query = "SELECT * FROM " + UserType.client.getTable().table() + " WHERE rut = ?";
        PreparedStatement preparedStatement = dataBase.getCONNECTION().prepareStatement(query);
        preparedStatement.setInt(1, client.getRut());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int rut = resultSet.getInt("rut");
            String name = resultSet.getString("name");
            String lastname = resultSet.getString("lastname");
            int age = resultSet.getInt("age");

            infoList.add(String.valueOf(rut));
            infoList.add(name);
            infoList.add(lastname);
            infoList.add(String.valueOf(age));
        }
        resultSet.close();
        preparedStatement.close();
        return infoList;
    }

    /**
     * Comprueba si un cliente existe en la base de datos.
     * @param client Es el cliente a consultar.
     * @return Devuelve true/false en funcion de su existencia.
     */
    @SneakyThrows
    public boolean exists(Client client) {
        String query = "SELECT * FROM " + UserType.client.getTable().table() + " WHERE rut = ?";
        PreparedStatement preparedStatement = dataBase.getCONNECTION().prepareStatement(query);
        preparedStatement.setInt(1, client.getRut());
        ResultSet resultSet = preparedStatement.executeQuery();

        int rut=0;

        if (resultSet.next()) rut = resultSet.getInt("rut");
        resultSet.close();
        preparedStatement.close();
        return rut == client.getRut();
    }
}
