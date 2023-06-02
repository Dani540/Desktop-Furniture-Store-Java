package com.furniturestore.models.entity.furniture;

public class FurnitureFactory implements IFurnitureFactory {
    @Override
    public Furniture createTraditionalFurniture(int id, String name, String description, double weight, double price) {
        return new TraditionalFurniture(id, name, description, weight, price);
    }

    @Override
    public Furniture createPersonalizedFurniture(int id, String name, String description, double weight, double price, boolean requireBuild) {
        return new PersonalizedFurniture(id, name, description, weight, price, requireBuild);
    }
}
