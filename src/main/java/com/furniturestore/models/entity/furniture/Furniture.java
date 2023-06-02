package com.furniturestore.models.entity.furniture;

import com.furniturestore.models.entity.Entity;
import lombok.Getter;

@Getter
public class Furniture extends Entity {
    private final int id;
    private final String name;
    private final String description;
    private final double weight;
    private final double price;

    public Furniture(int id, String name, String description, double weight, double price) {
        this.id = id;
        this.name = formatToSnakeCase(name);
        this.description = formatToSnakeCase(description);
        this.weight = weight;
        this.price = price;
    }

    private String formatToSnakeCase(String string){
        return String.join("_", string.split(" "));
    }
    private String formatToSpaces(String string){
        return String.join(" ", string.split("_"));
    }
}
