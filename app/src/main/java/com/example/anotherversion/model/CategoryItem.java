package com.example.anotherversion.model;

public class CategoryItem {

    int id;
    String name;
    float cost;

    public CategoryItem(int id, String name, float cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
