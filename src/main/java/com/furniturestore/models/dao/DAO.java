package com.furniturestore.models.dao;

import com.furniturestore.models.entity.Entity;
import com.others.formsSystem.EnumType;

public abstract class DAO implements EnumType {
    /**
     * Para añadir entidades.
     * @param entity Es la entidad a añadir.
     */
    public abstract void addEntity(Entity entity);

    /**
     * Para obtener identificadores.
     * @param type Es el tipo de entidad a la que nos referiremos.
     * @return Devuelve el identificador.
     */
    public abstract int getId(EnumType type);
}
