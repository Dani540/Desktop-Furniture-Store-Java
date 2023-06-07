/**
 * Esta clase gestiona la base de datos y todas las acciones que realizemos con esta.
 */

package com.repository;

import com.customerrors.ConnectionError;
import com.furniturestore.models.entity.users.Client;
import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.FurnitureType;
import com.others.formsSystem.UserType;
import com.services.ClientServices;
import com.services.FurnitureService;
import com.services.UserService;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;

public class DataBase {

    private final String DRIVER;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final UserService userService;
    private final FurnitureService furnitureService;
    private final ClientServices clientServices;

    /**
     * El constructor establece la conexion con la base de datos,
     * define una instancia a nivel de clase de UserService para todo el manejo de datos
     * correspondiente a los usuarios y define una instancia a nivel de clase
     * FurnitureService para todo el manejo de datos de los muebles.
     */
    public DataBase() {
        DRIVER = "com.mysql.cj.jdbc.Driver";
        URL = "jdbc:mysql://localhost/furnituresale";
        USER = "root";
        PASSWORD = "";

        userService = new UserService(this);
        furnitureService = new FurnitureService(this);
        clientServices = new ClientServices(this);
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
        }catch (ConnectionError e){
            System.out.println("Error al conectar en la base de datos: " + e);
        }catch (Exception e){
            System.out.println("Error Desconocido");
        }
        return connection;
    }

    /**.
     * @return Devuelve una conexion valida.
     */
    public Connection getCONNECTION() {
        return connection();
    }

    /**
     * Añade un usuario a la tabla de usuarios de la base de datos.
     * Usa la instancia userService para esto.
     * @param user Es el usuario a introducir.
     */
    public void setUser(User user){
        userService.addEntity(user);
    }

    /**
     * Obtiene una lista de todos los usuarios en la base de datos.
     * Usa la instancia de userService para esto.
     * @return Devuelve una lista con todos los usuarios.
     */
    public List<User> getUsers(){
        return userService.getEntities();
    }

    /**
     * Comprueba si el usuario indexeado existe en la base de datos.
     *
     * @param user Es el usuario a verificar
     * @return Devuelve si el usuario existe.
     */
    public int stateOfUser(User user){
        return userService.stateOfUser(user);
    }

    /**
     * Obtiene un identificar unico para cada tipo de mueble.
     * Utiliza la instancia de furnitureService para esto.
     * @param type Es el tipo de mueble para el que queremos el identificador.
     * @return Devuelve el identificador obtenido en funcion del tipo de mueble especificado.
     */
    public int getFurnitureId(FurnitureType type) {
        return furnitureService.getId(type);
    }

    /**
     * Añade un nuevo mueble a la base de datos.
     * Utiliza la instancia de furnitureService para esto.
     * @param furniture Es el mueble a registrar.
     */
    public void addFurniture(Furniture furniture) {
        furnitureService.addEntity(furniture);
    }

    /**
     * Obtiene los muebles de la base de datos.
     * Utiliza la instancia de furnitureService para esto.
     * @return Devuelve una lista con objetos de todos los muebles obtenidos.
     */
    public List<Furniture> getFurniture(){
        return furnitureService.getEntities();
    }

    /**
     * Obtiene los muebles de la base de datos en funcion del tipo
     * de mueble indexeado. Utiliza la instancia de furnitureService para esto.
     * @param type Es el tipo de mueble.
     * @return Devuele una lista con objetos de todos los muebles obtenidos en funcion del tipo de mueble.
     */
    public List<Furniture> getFurniture(FurnitureType type){
        return furnitureService.getEntities(type);
    }

    /**
     * Añade los muebles seleccionados para la venta a la tabla
     * de venta para una nueva venta. Utiliza la instancia de
     * furnitureService para esto.
     * @param selectedFurniture Es la lista con los muebles seleccionados.
     */
    public void addToSale(List<Furniture> selectedFurniture) {
        furnitureService.addToSale(selectedFurniture);
    }

    public void addToSale(Furniture furniture) {
        furnitureService.addToSale(furniture);
    }

    /**
     * Resetea la tabla de venta una vez realizada o cancelada esta.
     * Utiliza la instancia de furnitureService para esto.
     */
    public void clearSale() {
        furnitureService.clearSale();
    }

    /**
     * Obtiene los muebles seleccionados para la venta
     * de la base de datos.
     * @return Devuele una lista de objetos de los muebles seleccionados para la venta.
     */
    @SneakyThrows
    public List<Furniture> getSale() {
        return furnitureService.getSale();
    }

    /**
     * Remueve un mueble de la base de datos de su respectiva tabla.
     * @param idSelected Es el identificador del mueble a remover.
     * @param typeSelected Es el tipo de mueble del mueble a remover.
     */
    public void removeFurniture(int idSelected, FurnitureType typeSelected) {
        furnitureService.remove(idSelected, typeSelected);
    }

    /**
     * Añade un objeto cliente a su tabla en la base de datos.
     * @param client Es el cliente para añadir.
     */
    public void addClient(Client client) {
        clientServices.addEntity(client);
    }

    /**
     * Obtiene el tipo de usuario registrado segun los datos actuales.
     * @param userData Son los datos del cliente.
     * @return Devuelve el tipo de usuario. (root, admin o vendor)
     */
    public UserType getUserType(String[] userData) {
        return userService.getUserType(userData);
    }

    public UserType getUserType() {
        return userService.getUserType();
    }

    /**
     * Define el tipo de usuario del usuario actual.
     * Este metodo sirve para todo el funcionamiento posterior
     * segun los permisos del tipo de usuario.
     * @param userType Es el tipo de usuario a establecer.
     */
    public void setUserType(UserType userType) {
        userService.setUserType(userType);
    }

    /**
     * Obtiene un lista con la informacion del cliente.
     * @param client Es el cliente a leer.
     * @return Devuelve la lista con la informacion en forma de String.
     */
    public List<String> getClientInfo(Client client) {
        return clientServices.getInfo(client);
    }

    /**
     * Comprueba que un cliente exista.
     * @param client Es el cliente a comprobar.
     * @return Devuelve true/false en funcion de si existe o no.
     */
    public boolean isClientExists(Client client) {
        return clientServices.exists(client);
    }

    /**
     * Borra todos los muebles en la base de datos indiferente a su tipo.
     */
    public void removeAllFurniture() {
        furnitureService.remove(FurnitureType.traditional);
        furnitureService.remove(FurnitureType.personalized);
    }

    /**
     * Borra solo los muebles tradicionales de la base de datos.
     */
    public void removeTradFurniture() {
        furnitureService.remove(FurnitureType.traditional);
    }

    /**
     * Borra solo los muebles personalizados de la base de datos.
     */
    public void removePerFurniture() {
        furnitureService.remove(FurnitureType.personalized);
    }

    /**
     * Obtiene la cantidad de muebles registrados en funcion de su tipo.
     * @param furnitureType Es el tipo de mueble a consultar.
     * @return Devuelve la cantidad de muebles registrados.
     */
    public int getFurnitureQuantity(FurnitureType furnitureType) {
        return furnitureService.getQuantities(furnitureType);
    }

    /**
     * Obtiene la fecha actual.
     * @return Devuelve la fecha en forma de String.
     */
    public String getDate() {
        return new Date().toString();
    }

    public void removeUser(User user) {
        userService.removeUser(user);
    }

    public User getUser(User user) {
        return userService.getUser(user);
    }

    public boolean isUserExists(User user) {
        return userService.isUserExits(user);
    }

    public boolean isFurnitureExists(Furniture furniture, FurnitureType type) {
        return furnitureService.isFurnitureExists(furniture, type);
    }
}
