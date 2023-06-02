package com.others.furnitureSystem;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.dao.PersonalizedFurnitureSelectorDAO;
import com.furniturestore.models.dao.TraditionalSelectorDAO;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.formsSystem.FurnitureType;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class FurnitureILister implements ILister<Furniture, Label, FurnitureType> {
    /**
     * Carga la estructura de la lista y sus elementos dependiendo de su implementacion.
     */
    @Override
    public abstract void loadList();
    /**
     * Carga la lista de muebles sin distincion desde la base de datos.
     * @return Devuelve la lista de instancias cargadas.
     */
    @Override
    public List<Furniture> loadEntities() {
        return FurnitureStoreApp.getDataBase().getFurniture();
    }
    /**
     * Carga la lista de muebles segun su tipo usando la instancia DAO correspondiente.
     * @param type Es el typo de mueble (tradicional o personalizado)
     * @return Devuelve la lista de muebles.
     */
    @Override
    public List<Furniture> loadEntities(FurnitureType type) {
        return type.equals(FurnitureType.traditional) ? new TraditionalSelectorDAO().loadTraditionals() : new PersonalizedFurnitureSelectorDAO().loadPersonalizeds();
    }
    /**
     * Carga la info de las entidades cargadas anteriormente para
     * listarlas graficamente en la pantalla.
     * @param entities Es la lista de entidades
     * @return devuelve una lista de etiquetas con los nombres y el id de estas.
     */
    @Override
    public List<Label> loadEntityInfo(List<Furniture> entities) {
        List<Label> labels = new ArrayList<>();
        IntStream.range(0, entities.size()).forEach(i ->{
            Label label = new Label(" #" + entities.get(i).getId() + " " + entities.get(i).getName() + " ");
            labels.add(label);
        });
        return labels;
    }

    /**
     * Este metodo escribe la informacion del mueble seleccionado en un nodo predefinido.
     * En este caso lo hace en funcion de si el checkbox correspondiente a la lista de checkboxes
     * con la lista de muebles (ambas listas con get(i) ) esta seleccionado.
     *
     * @param traditionalFurnitures Lista de muebles tradicionales.
     * @param i                     El mueble seleccionado de la lista de muebles.
     * @return devuelve unan lista con etiquetas que contienen la info del mueble
     */
    protected List<Label> loadEntityDescription(List<Furniture> traditionalFurnitures, int i) {
        List<Label> labels = new ArrayList<>();

        labels.add(new Label(" " + traditionalFurnitures.get(i).getName() + " " ));
        labels.add(new Label(" " + traditionalFurnitures.get(i).getDescription() + " " ));
        labels.add(new Label(" " + traditionalFurnitures.get(i).getWeight() + " "));
        labels.add(new Label(" " + traditionalFurnitures.get(i).getPrice() + " "));

        return labels;
    }
}
