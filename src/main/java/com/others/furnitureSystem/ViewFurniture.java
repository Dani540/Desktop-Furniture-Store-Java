package com.others.furnitureSystem;

import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.dao.ViewFurnitureDAO;
import com.furniturestore.models.entity.furniture.Furniture;
import com.furniturestore.models.entity.furniture.TraditionalFurniture;
import com.others.formsSystem.FurnitureType;
import com.others.sceneSystem.Scenes;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class ViewFurniture extends FurnitureILister {
    @Getter
    private final ViewFurnitureDAO viewFurnitureDAO;
    @Setter
    private  VBox listVBox;
    @Setter
    private  VBox infoVBox;
    private int idSelected;
    @Setter
    private FurnitureType typeSelected;

    public ViewFurniture(ViewFurnitureDAO viewFurnitureDAO) {
        this.viewFurnitureDAO = viewFurnitureDAO;
        idSelected = -1;
    }

    @Override
    public void loadList() {
        List<Furniture> furnitures = typeSelected == null ? loadEntities() : loadEntities(typeSelected);

        List<Label> labels = loadEntityInfo(furnitures);
        List<Button> buttons = loadButtons(labels);

        setButtonAction(buttons, furnitures);
        listVBox.getChildren().addAll( buttons );
    }

    private List<Button> loadButtons(List<Label> labels) {
        List<Button> buttons = new ArrayList<>();
        for (Label n : labels) buttons.add( new Button(n.getText()) );
        return buttons;
    }

    private void setButtonAction(List<Button> buttons, List<Furniture> furnitures) {
        IntStream.range(0, buttons.size()).forEach(i ->{
            buttons.get(i).setOnAction(actionEvent ->{
                infoVBox.getChildren().setAll(loadEntityDescription(furnitures, i));
                idSelected = furnitures.get(i).getId();
                typeSelected = furnitures.get(i) instanceof TraditionalFurniture ? FurnitureType.traditional : FurnitureType.personalized;
            });
        });
    }

    public void onRemoveButton() {
        viewFurnitureDAO.removeFurniture(idSelected, typeSelected);
        SceneController.switchScene(Scenes.viewAllFurniture);
    }
}
