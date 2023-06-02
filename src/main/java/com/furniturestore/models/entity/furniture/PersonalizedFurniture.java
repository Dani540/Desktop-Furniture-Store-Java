package com.furniturestore.models.entity.furniture;

public class PersonalizedFurniture extends Furniture {
    private final boolean requireBuild;
    public PersonalizedFurniture(int id, String name, String description, double weight, double price, boolean requireBuild) {
        super(id, name, description, weight, price);
        this.requireBuild = requireBuild;
    }

    public boolean getRequireBuild() {
        return requireBuild;
    }

}
