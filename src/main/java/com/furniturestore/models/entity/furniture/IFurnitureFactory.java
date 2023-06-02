package com.furniturestore.models.entity.furniture;

public interface IFurnitureFactory {
    Furniture createTraditionalFurniture(int id, String name, String description, double weight, double price);
    Furniture createPersonalizedFurniture(int id, String name, String description, double weight, double price, boolean requireBuild);
}
