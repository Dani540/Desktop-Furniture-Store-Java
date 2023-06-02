/**
 * Clase encargada de la gestion de datos de usuarios en al base de datos.
 */

package com.services;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.UserType;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService{
    @Setter
    private UserType userType;

    /**
     * Comprueba si el usuario ingresado existe en la base de datos.
     * Retorna el estado, 1 para un usuario completo y 2 para una contraseña incorrecta.
     * @param user Es el usuario ingresado.
     * @return Devuelve 1 o 2 en funcion de si existe y sus datos estan correctos, devuelve 0 si no existe.
     */
    public int isUserExits(User user) {
        int state = 0;  // No existe
        List<User> users = getUsers();
        if (users.stream().anyMatch(n -> n.getData().equals(user.getData()) && n.getUserType().equals(user.getUserType())))
            state = 1;  // Existe
        else if (users.stream().anyMatch(n -> n.getUsername().equals(user.getUsername()) && n.getUserType().equals(user.getUserType())))
            state = 2;  // Contraseña incorrecta
        return state;
    }


    /**
     * Registra usuarios en la base de datos.
     * @param user Es el usuario a registrar.
     */
    @SneakyThrows
    public void setUser(User user){
        Connection connection = FurnitureStoreApp.getDataBase().getCONNECTION();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password, usertype) VALUES (?,?,?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, userType.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * Obtiene una lista con los usuarios en la base de datos.
     * @return Devuelve la lista de objetos usuarios.
     */
    @SneakyThrows
    public List<User> getUsers() {
        Connection connection = FurnitureStoreApp.getDataBase().getCONNECTION();

        List<User> list = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from users ");

        while ( resultSet.next() ){
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            UserType userType = getUserType(resultSet);
            list.add( new User(id, username, password, userType) );
        }

        return list;
    }

    /**
     * Obtiene el tipo de usuario ingresado a partir de los datos en la base de datos.
     * @param resultSet Es el set de datos de usuario obtenido de la base de datos.
     * @return Devuelve el tipo de usuario de tipo constante de UserType.
     */
    @SneakyThrows
    private UserType getUserType(ResultSet resultSet) {
        UserType userType = null;
        switch (resultSet.getInt("usertype")){
            case 0 -> userType = UserType.root;
            case 1 -> userType = UserType.vendor;
            case 2 -> userType = UserType.admin;
            case 3 -> userType = UserType.client;
        }
        return userType;
    }

    /**
     * Obtiene el tipo de usuario ingresado a partir de los datos ingresados.
     * @param userData Son los datos ingresados. (Username & Password)
     * @return Devuelve el tipo de usuario de tipo constante de UserType.
     */
    public UserType getUserType(String[] userData) {
        return userType;
    }
}
