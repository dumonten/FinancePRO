package com.example.anotherversion.model;

public class CategoryItem {

    int id;
    String name;
    float cost;
    String date;
    long dateSec;

    public CategoryItem(int id, String name, float cost, String date, long dateSec) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.dateSec = dateSec;
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

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public long getDateSec() { return dateSec; }

    public void setDate(long dateSec) { this.dateSec = dateSec; }
}
