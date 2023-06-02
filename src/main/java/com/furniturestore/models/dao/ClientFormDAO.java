package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.users.Client;
import com.furniturestore.models.entity.Entity;
import com.others.formsSystem.EnumType;
import com.others.formsSystem.UserType;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.List;

public class ClientFormDAO extends DAO {

    @Override
    public void addEntity(Entity entity) {
        FurnitureStoreApp.getDataBase().addClient( (Client) entity);
    }

    /**
     * Un-use
     */
    @Override
    public int getId(EnumType type) {
        return 0;
    }

    /*
    * Un-use
     */
    @Override
    public Entity getInstance(int entityId, Node[] data) {
        return null;
    }

    /**
     * Obtiene la informacion del cliente.
     * @param data Son los campos del formulario.
     * @return Devuelve una lista de String.
     */
    public List<String> getInstanceInfo(Node[] data){
        int rut = Integer.parseInt(((TextField) data[0]).getText());
        return FurnitureStoreApp.getDataBase().getClientInfo(new Client(rut));
    }

    /**
     * Comprueba que el cliente ingresado existe en la base de datos.
     * @param data Son los campos del formulario.
     * @return Devuelve true o false en funcion de si existe o no.
     */
    public boolean isClientExists(Node[] data) {
        int rut = Integer.parseInt( ( (TextField) data[0]).getText() );
        Client client = null;
        try{
            String name = ((TextField) data[1]).getText();
            String lastname = ((TextField) data[2]).getText();
            client = new Client(rut,name,lastname);
        }catch (RuntimeException e){
            client = new Client(rut);
        }
        return FurnitureStoreApp.getDataBase().isClientExists(client);
    }
}
