package com.furniturestore.views;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.TraditionalSelectorDAO;
import com.furniturestore.models.entity.furniture.Furniture;
import com.others.sceneSystem.AuxiliaryWindow;
import com.others.furnitureSystem.FurnitureILister;
import com.others.formsSystem.FurnitureType;
import com.others.sceneSystem.Scenes;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TraditionalSelectorView extends FurnitureILister {
    private final VBox leftVBox, rightVBox, leftCheckBoxVBox;
    private final List<Furniture> selectedFurniture;
    private final TraditionalSelectorDAO traditionalSelectorDAO;

    public TraditionalSelectorView(VBox leftVBox, VBox rightVBox, VBox leftCheckBoxVBox) {
        this.leftVBox = leftVBox;
        this.rightVBox = rightVBox;
        this.leftCheckBoxVBox = leftCheckBoxVBox;
        selectedFurniture = new ArrayList<>();
        traditionalSelectorDAO = new TraditionalSelectorDAO();
    }

    /**
     * Esta implementacion de FurnitureILister carga toda la estructura y elementos de la lista.
     */
    @Override
    public void loadList() {
        List<Furniture> traditionalFurnitures = loadEntities(FurnitureType.traditional);
        List<Label> labels = loadEntityInfo(traditionalFurnitures);
        List<CheckBox> checkBoxes = loadCheckBoxes(traditionalFurnitures.size());

        setCheckListener(checkBoxes, traditionalFurnitures);

        leftVBox.getChildren().addAll(labels);
        leftCheckBoxVBox.getChildren().addAll(checkBoxes);
    }

    /**
     * Este metodo añade a la lista de compra los muebles seleccionados.
     * @param traditionalFurnitures Es la lista de muebles.
     * @param sel Comprueba que el mueble este seleccionado a traves de un checkbox (isSelected : true | false).
     * @param i Es el mueble seleccionado de la lista de muebles.
     */
    private void addToSale(List<Furniture> traditionalFurnitures, Boolean sel, int i) {
        if (sel) selectedFurniture.add(traditionalFurnitures.get(i));
        else selectedFurniture.remove(traditionalFurnitures.get(i));
    }

    /**
     * Carga la lista de checkboxes.
     * @param amount Cantidad de checkboxes a cargar.
     * @return devuelve una lista de checkboxes.
     */
    private List<CheckBox> loadCheckBoxes(int amount) {
        return Stream.generate(CheckBox::new).limit(amount).toList();
    }

    private void setCheckListener(List<CheckBox> checkBoxes, List<Furniture> traditionalFurnitures) {
        IntStream.range( 0,traditionalFurnitures.size() ).forEach(i ->{
            checkBoxes.get(i).selectedProperty().addListener( (obs, des, sel) ->{
                addToSale(traditionalFurnitures, sel, i);
                if (sel){
                    rightVBox.getChildren().clear();
                    rightVBox.getChildren().addAll(loadEntityDescription(traditionalFurnitures, i));
                }
            } );
        });
    }

    public void onConfirmButton() {
        traditionalSelectorDAO.addToSale(selectedFurniture);
        SceneController.switchScene(Scenes.newSale);
    }

    public void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }

    public void onCancelButton() {
        SceneController.switchScene(Scenes.newSale);
    }
}
