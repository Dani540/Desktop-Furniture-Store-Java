/**
 * Clase encargada de la gestion de datos de los muebles en la base de datos.
 */

package com.services;

import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.FurnitureFactory;
import com.furniturestore.models.entity.furniture.PersonalizedFurniture;
import com.furniturestore.models.entity.furniture.TraditionalFurniture;
import com.others.formsSystem.FurnitureType;
import com.repository.DataBase;
import com.repository.Repository;
import com.repository.Table;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FurnitureService implements IService<Furniture, FurnitureType> {
    /**
     * El constructor recibe la base de datos.
     */
    private final Table saleTable;
    private final DataBase dataBase;
    public FurnitureService(DataBase dataBase) {
        this.dataBase = dataBase;
        saleTable = new Table("sale", List.of("furnitureId", "type"));
    }

    /**
     * Este metodo se utiliza para asignar un ID a un nuevo mueble.
     * Lo hace obteniendo el Id del ultimo elemento de la tabla correspondiente.
     * @param type Este valor indica el tipo de mueble.
     *                      o uno personalizado (false).
     * @return Devuelve el ID correspondiente.
     */
    @SneakyThrows
    public int getId(FurnitureType type) {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM " + type.getTable().table());
        int id = 0;
        while (resultSet.next()) id = resultSet.getInt("id");
        resultSet.close();
        return id;
    }

    /**
     * Esta implementacion del metodo addEntity registra un nuevo mueble en la base de datos,
     * en la tabla correspondiente a su tipo.
     * @param entity Es el mueble registrado.
     */
    @SneakyThrows
    @Override
    public void addEntity(Furniture entity) {
        if (entity==null) return;
        Connection connection = dataBase.getCONNECTION();
        boolean isTrad = entity instanceof  TraditionalFurniture;
        Table tradTable = FurnitureType.traditional.getTable();
        Table persTable = FurnitureType.personalized.getTable();
        String statement = isTrad
                ? insertInto(tradTable.table(), tradTable.values())
                : insertInto(persTable.table(), persTable.values());
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setDouble(3, entity.getWeight());
        preparedStatement.setDouble(4, entity.getPrice());
        if (!isTrad) preparedStatement.setBoolean(5,( (PersonalizedFurniture) entity).getRequireBuild());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    /**
     * Esta implementacion del metodo getEntities lee todos los muebles registrados en la
     * base de datos y devuelve una lista con las instancias de los muebles.
     * @return devuelve una lista de instancias de furniture.
     */
    @SneakyThrows
    @Override
    public List<Furniture> getEntities() {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();
        return Stream.concat( getTraditionalFurniture(statement).stream(),  getPersonalizedFurniture(statement).stream() ).toList();
    }

    /**
     * Esta implementacion del metodo getEntities lee todos los muebles registrados en la
     * tabla correspondiente a la indicada en el parametro,
     * y devuelve una lista de instancias de la clase padre
     * con estos valores.
     * @param type Si es tradicional o no (personalizado).
     * @return devuelve una lista de instancias de furniture.
     */
    @SneakyThrows
    @Override
    public List<Furniture> getEntities(FurnitureType type) {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();
        return type == FurnitureType.traditional ? getTraditionalFurniture(statement) : getPersonalizedFurniture(statement);
    }

    /**
     * Este metodo recoge una lista de objetos de muebles tradicionales de la tabla
     * de estos en la base da datos.
     * @param statement Es el encargado de gestionar la consula sql.
     * @return devuelve la lista de objetos de muebles tradicionales.
     */
    @SneakyThrows
    private List<Furniture> getTraditionalFurniture(Statement statement) {
        ResultSet resultSet = statement.executeQuery("Select * from " + FurnitureType.traditional.getTable().table());

        List<Furniture> list = new ArrayList<>();

        FurnitureFactory furnitureFactory = new FurnitureFactory();

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double weight = resultSet.getDouble("weight");
            double price = resultSet.getDouble("price");

            list.add( furnitureFactory.createTraditionalFurniture(id, name, description, weight, price) );
        }
        resultSet.close();
        return list;
    }
    /**
     * Este metodo recoge una lista de objetos de muebles personalizados de la tabla
     * de estos en la base da datos.
     * @param statement Es el encargado de gestionar la consula sql.
     * @return devuelve la lista de objetos de muebles personalizados.
     */
    @SneakyThrows
    private List<Furniture> getPersonalizedFurniture(Statement statement) {
        ResultSet resultSet = statement.executeQuery("Select * from " + FurnitureType.personalized.getTable().table());

        List<Furniture> list = new ArrayList<>();

        FurnitureFactory furnitureFactory = new FurnitureFactory();

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            double weight = resultSet.getDouble("weight");
            double price = resultSet.getDouble("price");
            boolean requireBuild = resultSet.getBoolean("requireBuild");
            list.add( furnitureFactory.createPersonalizedFurniture(id, name, description, weight, price, requireBuild) );
        }
        resultSet.close();
        return list;
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

    /**
     * Este metodo añade una lista de muebles seleccionados a la venta (tabla).
     * @param selectedFurniture Es la lista de muebles seleccionados.
     */
    @SneakyThrows
    public void addToSale(List<Furniture> selectedFurniture) {
        selectedFurniture.forEach(this::addToSale);
    }

    /**
     * Este metodo añade un mueble a la venta (tabla).
     * @param furniture Es el mueble a añadir.
     */
    @SneakyThrows
    public void addToSale(Furniture furniture) {
        Connection connection = dataBase.getCONNECTION();

        String type = furniture instanceof TraditionalFurniture ? "traditional" : "personalized";
        PreparedStatement preparedStatement = connection.prepareStatement(insertInto(saleTable.table(), saleTable.values()));
        preparedStatement.setInt(1, furniture.getId());
        preparedStatement.setString(2, type);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * Limpia la tabla de venta.
     */
    @SneakyThrows
    public void clearSale() {
        Connection connection = dataBase.getCONNECTION();

        Statement statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE sale");
        statement.close();
    }

    /**
     * Obtiene la actual venta.
     * @return Devuelve un lista de instacias Furniture con los muebles para la venta.
     */
    @SneakyThrows
    public List<Furniture> getSale(){
        Connection connection = dataBase.getCONNECTION();

        Statement statement =  connection.createStatement();
        List<Furniture> sale = new ArrayList<>(),
                traditional = getEntities(FurnitureType.traditional),
                personalized = getEntities(FurnitureType.personalized);
        ResultSet resultSet = statement.executeQuery("Select * from sale");

        while (resultSet.next()){
            int id = resultSet.getInt("furnitureId");
            if (resultSet.getString("type").equals("traditional")){
                for (Furniture f : traditional){
                    if (f.getId() == id) sale.add(f);
                }
            }
            else{
                for (Furniture f : personalized) {
                    if (f.getId() == id) sale.add(f);
                }
            }
        }
        resultSet.close();
        statement.close();

        return sale;
    }

    /**
     * Remueve un mueble de su respectiva tabla.
     * @param idSelected Es el identificador del mueble a remover.
     * @param typeSelected Es el tipo de mueble a remover.
     */
    @SneakyThrows
    public void remove(int idSelected, FurnitureType typeSelected) {
        Connection connection = dataBase.getCONNECTION();

        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM " + typeSelected.getTable().table() + " WHERE id = " + idSelected);
        statement.close();
    }

    /**
     * Remueve todos los muebles de su respectiva tabla.
     * @param type Es el tipo de los muebles a remover.
     */
    @SneakyThrows
    public void remove(FurnitureType type) {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();
        switch (type){
            case traditional -> removeTrads(statement);
            case personalized -> removePers(statement);
        }
    }

    /**
     * Remueve todos los muebles personalizados de su respectiva tabla.
     * @param statement Es la sentencia SQL a ejecutar.
     */
    @SneakyThrows
    public void removePers(Statement statement) {
        statement.execute("TRUNCATE TABLE " + FurnitureType.personalized.getTable().table());
    }

    /**
     * Remueve todos los muebles tradicionales de su respectiva tabla.
     * @param statement Es la sentencia SQL a ejecutar.
     */
    @SneakyThrows
    public void removeTrads(Statement statement) {
        statement.execute("TRUNCATE TABLE " + FurnitureType.traditional.getTable().table());
    }

    /**
     * Obtiene la cantidad de muebles en la base de datos.
     * @return Devuelve la cantidad de muebles obtenida.
     */
    public int getQuantities() {
        return getEntities().size();
    }

    /**
     * Es la cantidad de muebles en la base de datos respecto a su tipo.
     * @param furnitureType Es el tipo de muebles a consultar.
     * @return Devuelve la cantidad de muebles de ese tipo obtenida.
     */
    public int getQuantities(FurnitureType furnitureType) {
        return getEntities(furnitureType).size();
    }

    public boolean isFurnitureExists(FurnitureType type) {
        List<Furniture> furnitures = getEntities(type);
        return furnitures.contains(furnitures);
    }
}
