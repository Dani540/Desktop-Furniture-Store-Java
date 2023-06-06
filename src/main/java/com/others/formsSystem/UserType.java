/**
 * En este enumerador las constantes son los tipos de usuario que pueden ingresar al sistema.
 * Se le asigna a cada una segun su id y su instanciacion en funcion a tu tipo de usuario.
 */

package com.others.formsSystem;

import com.furniturestore.models.entity.users.Client;
import com.furniturestore.models.entity.Entity;
import com.furniturestore.models.entity.users.User;
import com.repository.Table;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.util.List;

@Getter
public enum UserType implements EnumType {
    root(0), vendor(1), admin(2), client(3);
    private final int id;
    private String symbol;
    private Table table;
    private int beginIndex;

    UserType(int id) {
       this.id=id;
       setTable(id);
       setSymbol(id);
       setBeginIndex(id);
    }

    private void setBeginIndex(int id) {
        switch (id){
            case 0,2 -> beginIndex = 1;
            case 1 -> beginIndex = 0;
        }
    }

    private void setTable(int id){
        switch (id){
            case 0,1,2 -> table = new Table("users", List.of("username", "password", "usertype"));
            case 3 -> table = new Table("clients", List.of("rut", "name", "lastname", "age"));
        }
    }

    public Table getTable(){
        return table;
    }

    @Override
    public Entity getInstance(int entityId, Node ...data){
        Entity entity = null;
        switch (id){
            case 0 -> entity = getRoot(data);
            case 1 -> entity = getVendor(data);
            case 2 -> entity = getAdmin(data);
            case 3 -> entity = getClient(data);
        }
        return entity;
    }

    private Entity getRoot(Node[] data) {
        return getUser(data, root);
    }

    private Entity getVendor(Node[] data) {
        return getUser(data, vendor);
    }

    private Entity getAdmin(Node[] data) {
        return getUser(data, admin);
    }

    private Entity getUser(Node[] data, UserType type) {
        String name = ( (TextField) data[0] ).getText();
        String password = ( (TextField) data[1] ).getText();

        return new User(name, password, type);
    }

    private Entity getClient(Node[] data) {
        Client client;
        try{
            int rut = Integer.parseInt(((TextField) data[0]).getText());
            String name = ((TextField) data[1]).getText();
            String lastname = ((TextField) data[2]).getText();

            TextField aux = ((TextField) data[3]);
            int age = 0;
            //if ( !aux.getText().isEmpty() || !aux.getText().isBlank() || aux.getText() != null) age = Integer.parseInt( aux.getText() );
            if (!aux.getText().equals("")) age = Integer.parseInt(aux.getText());
            client = new Client(rut, name, lastname, age);
        }catch (RuntimeException e){
            int rut = Integer.parseInt(((TextField) data[0]).getText());
            client = new Client(rut);
        }
        return client;
    }

    private void setSymbol(int id) {
        switch (id){
            case 0 -> symbol = "/";
            case 1, 3 -> symbol = "";
            case 2 -> symbol = ".";
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public int getBeginIndex() {
        return beginIndex;
    }
}
