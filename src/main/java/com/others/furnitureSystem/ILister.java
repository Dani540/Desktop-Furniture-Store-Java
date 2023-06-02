/**
 * Esta interfaz se utiliza para las listas
 * con elementos graficos de entidades.
 */

package com.others.furnitureSystem;

import java.util.List;

public interface ILister<E, L, T> {
    /**
     * Carga la estructura de la lista
     */
    void loadList();

    /**
     * Carga todas las entidades
     * @return devuelve una lista con la entidad E definida en su implementacion
     */
    List<E> loadEntities();

    /**
     * Carga las entidades segun el tipo de entidad definido en su implementacion.
     * @param type Es el tipo de entidad
     * @return Devuelve una lista con las entidades.
     */
    List<E> loadEntities(T type);

    /**
     * Carga la informacion definida en la implementacion
     * @param entities Es la lista de entidades obtenida en la carga de entidades.
     * @return Devuelve una lista de elementos definidos en su implementacion.
     */
    List<L> loadEntityInfo(List<E> entities);

}
