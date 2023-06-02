/**
 * Esta interface se usa para el manejo de tipos de datos.
 * Esto se logra a traves de la implementacion de esta en los
 * enumeradores.
 */

package com.others.formsSystem;

import com.furniturestore.models.entity.Entity;
import javafx.scene.Node;

public interface EnumType {
    Entity getInstance(int entityId, Node[] data);
}
