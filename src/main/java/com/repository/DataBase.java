package com.repository;

import com.customerrors.ConnectionError;
import com.services.ClientService;
import com.services.FurnitureService;
import com.services.TicketService;
import com.services.UserService;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

    private static DataBase dataBase;
    @Getter
    private final Repository repository;
    private final String DRIVER;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final int limitReconnect = 3;


    /**
     * El constructor establece la conexion con la base de datos,
     * define una instancia a nivel de clase de UserService para todo el manejo de datos
     * correspondiente a los usuarios y define una instancia a nivel de clase
     * FurnitureService para todo el manejo de datos de los muebles.
     */
    private DataBase() {
        DRIVER = "com.mysql.cj.jdbc.Driver";
        URL = "jdbc:mysql://localhost/furnituresale";
        USER = "root";
        PASSWORD = "";
        repository = new Repository(
                new UserService(this),
                new FurnitureService(this),
                new ClientService(this),
                new TicketService(this));
    }

    public static DataBase getInstance(){
        if (dataBase==null) dataBase = new DataBase();
        return dataBase;
    }

    /**
     * Establece una conexion a la base de datos.
     * @return Devuelve la conexion.
     */
    @SneakyThrows
    private Connection connection()  {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection==null) throw new ConnectionError("Conexion Invalida");
        }catch (ConnectionError e){
            System.out.println("Error al conectar en la base de datos: " + e);
            for (int i = 0; i <= limitReconnect; i++) {
                ConnectionError.reconnect(this);
            }
        }catch (Exception e){
            System.out.println("Error Desconocido");
        }
        return connection;
    }

    /**.
     * @return Devuelve una conexion valida.
     */
    public Connection getCONNECTION() {
        return dataBase.connection();
    }


}
