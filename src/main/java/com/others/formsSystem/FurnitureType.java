/**
 * Este enumerador guarda los tipos de muebles, sus tablas en la base de datos y su instancia correspondiente para su instanciacion.
 */

package com.others.formsSystem;

import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.FurnitureFactory;
import com.repository.Table;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.util.List;

@Getter
public enum FurnitureType implements EnumType {
    traditional(0),
    personalized(1);

    private final int id;
    private final Table table;

    /**
     * El constructor define el ID de la constante y le asigna su
     * tabla en funcion de su identificador.
     * @param id Es el identificador unico del tipo de mueble.
     */
    FurnitureType(int id){
        this.id = id;
        this.table = setTable(id);
    }

    /**
     * Asigna a la constante una tabla de la base de datos segun el tipo de mueble
     * correspondiente.
     * @param id Es el identificador de la constante
     * @return Devuelve un objeto de tipo Table
     */
    private Table setTable(int id) {
        Table table = null;
        switch (id){
            case 0 -> table = new Table("traditionalfurnitures", List.of("name", "description", "weight","price"));
            case 1 -> table = new Table("personalizedfurnitures",List.of("name", "description", "weight", "price", "requireBuild") );
        }
        return table;
    }

    /**
     * Este metodo genera muebles en funcion de su tipo.
     * @param entityId Es el identificador unico del mueble.
     * @param data Son los datos del mueble a construir.
     * @return Devuelve un objeto de tipo furniture.
     */
    @Override
    public Furniture getInstance(int entityId, Node[] data) {
        Furniture furniture=null;
        try {
            FurnitureFactory furnitureFactory = new FurnitureFactory();

            String name = ( (TextField) data[0]).getText();
            String description = ( (TextArea) data[1]).getText();
            double weight = Double.parseDouble(((TextField) data[2]).getText());
            double price = Double.parseDouble(((TextField) data[3]).getText());
            if (id==0) furniture = furnitureFactory.createTraditionalFurniture(entityId, name, description, weight, price);
            else{
                boolean requireBuild = ( (CheckBox) data[4]).isSelected();
                furniture = furnitureFactory.createPersonalizedFurniture(entityId, name, description, weight, price, requireBuild);
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid number");
        }
        return furniture;
    }

}
