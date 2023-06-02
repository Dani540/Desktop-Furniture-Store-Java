package com.furniturestore.models.entity.users;

import com.furniturestore.models.entity.Entity;
import javafx.scene.control.skin.LabeledSkinBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Client extends Entity {
    private final int rut;
    private final String name;
    private final String lastName;
    private int age;

    public Client(int rut) {
        this.rut = rut;
        name = "";
        lastName = "";
    }

    public Client(int rut, String name, String lastname) {
        this.rut = rut;
        this.name = name;
        this.lastName = lastname;
    }
}
