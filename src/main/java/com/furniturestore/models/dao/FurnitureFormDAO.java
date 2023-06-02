/**
 * Esta clase se encarga de la gestion de datos
 * en la base de datos para los formularios de muebles.
 */

package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.entity.Entity;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.EnumType;
import com.others.formsSystem.FurnitureType;
import javafx.scene.Node;

public class FurnitureFormDAO extends DAO{
    /**
     * Añade un mueble a la tabla correspondiente al
     * tipo de mueble en la base de datos.
     * @param entity Es el mueble para añadir.
     */
    @Override
    public void addEntity(Entity entity) {
        FurnitureStoreApp.getDataBase().addFurniture( (Furniture) entity);
    }
    /**
     * Busca un identificador unico valido para cada tipo de mueble.
     * @param type Es el tipo de mueble para el que necesitamos un identificador.
     * @return Devuelve el identificador unico.
     */
    @Override
    public int getId(EnumType type) {
        return FurnitureStoreApp.getDataBase().getFurnitureId( (FurnitureType) type);
    }

    /**
     * Este metodo no se usa, está cacheado o algo y por eso sale
     * su uso, o hice alguna muy mala practica como para que pasase esto.
     * El sistema que involucra los formularios explica como se usan estos metodos.
     * @param entityId
     * @param data
     * @return
     */
    @Override
    public Entity getInstance(int entityId, Node[] data) {
        return null;
    }
}
