/**
 * Clase encargada de la gestion de datos de usuarios en al base de datos.
 */

package com.services;

import com.customerrors.InvalidUser;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.UserType;
import com.repository.DataBase;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class UserService implements IService<User, UserType>{
    private final DataBase dataBase;
    @Setter @Getter
    private UserType userType;

    public UserService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Comprueba si el usuario ingresado existe en la base de datos.
     * Retorna el estado, 1 para un usuario completo y 2 para una contraseña incorrecta.
     * @param user Es el usuario ingresado.
     * @return Devuelve 1 o 2 en funcion de si existe y sus datos estan correctos, devuelve 0 si no existe.
     */
    public int stateOfUser(User user){
        int state = 0;  // No existe
        List<User> users = getEntities();
        try {
            if (isUserExits(user))
                state = 1;  // Existe
            else if (users.stream().anyMatch(n -> n.getUsername().equals(user.getUsername()) && n.getUserType().equals(user.getUserType()))) {
                state = 2;  // Contraseña incorrecta
            }
            if (state==0||state==2) throw new InvalidUser(state);
        }catch (InvalidUser e){
            System.out.println(e);
        }
        return state;
    }

    /**
     * Comprueba si el usuario ingresado existe en la base de datos.
     * @param user Es el usuario ingresado.
     * @return Devuelve si existe o no.
     */
    public boolean isUserExits(User user){
        List<User> users = getEntities();
        return users.stream().anyMatch(n -> n.getData().equals(user.getData()) && n.getUserType().equals(user.getUserType()));
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

    /**
     * Registra usuarios en la base de datos.
     * @param entity Es el usuario a registrar.
     */
    @SneakyThrows
    @Override
    public void addEntity(User entity){
        Connection connection = dataBase.getCONNECTION();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password, usertype) VALUES (?,?,?)");
        preparedStatement.setString(1, entity.getUsername());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setInt(3, userType.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    /**
     * Obtiene una lista con los usuarios en la base de datos.
     * @return Devuelve la lista de objetos usuarios.
     */
    @SneakyThrows
    @Override
    public List<User> getEntities() {
        Connection connection = dataBase.getCONNECTION();

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

    @Override
    public List<User> getEntities(UserType type) {
        return null;
    }

    @SneakyThrows
    public void removeUser(User user) {
        Connection connection = dataBase.getCONNECTION();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM " + userType.getTable().table() + " WHERE id = " + user.getId());
        statement.close();
    }

    @SneakyThrows
    public User getUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = user.getId();

        try {
            connection = dataBase.getCONNECTION();
            statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? AND usertype = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getUserType().getId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } finally {
            // Cerrar los recursos en un bloque finally para asegurar su liberación
            assert resultSet != null;
            resultSet.close();
            statement.close();
            connection.close();
        }

        return new User(id, user.getUsername(), user.getPassword(), user.getUserType());
    }

}
