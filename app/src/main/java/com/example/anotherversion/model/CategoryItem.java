package com.example.anotherversion.model;

/**
 * The type Category item.
 */
public class CategoryItem {
    /**
     * The Id.
     */
    int id;
    /**
     * The Name.
     */
    String name;
    /**
     * The Cost.
     */
    float cost;
    /**
     * The Date.
     */
    String date;
    /**
     * The Date sec.
     */
    long dateSec;

    /**
     * Instantiates a new Category item.
     *
     * @param id      the id
     * @param name    the name
     * @param cost    the cost
     * @param date    the date
     * @param dateSec the date sec
     */
    public CategoryItem(int id, String name, float cost, String date, long dateSec) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.dateSec = dateSec;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() { return date; }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) { this.date = date; }

    /**
     * Gets date sec.
     *
     * @return the date sec
     */
    public long getDateSec() { return dateSec; }

    /**
     * Sets date sec.
     *
     * @param dateSec the date sec
     */
    public void setDateSec(long dateSec) { this.dateSec = dateSec; }
}