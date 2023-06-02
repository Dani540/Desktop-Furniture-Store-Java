/**
 * Esta interfaz se utiliza para las clases que
 * ofrezcan servicios a la base de datos.
 */

package com.services;

import java.util.List;

public interface IService<E, T> {
    void addEntity(E entity);
    List<E> getEntities();
    List<E> getEntities(T type);
}
