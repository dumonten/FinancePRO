package com.example.anotherversion.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryItemTest {
    int id;
    String name;
    float cost;
    String date;
    long dateSec;

    @Before
    public void setUp() throws Exception {
        id = 3;
        name = "Anton";
        cost = 12.3F;
        date = "12.12.2012";
        dateSec = 246257;
    }

    @Test
    public void testGetCost() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals((int)cost, (int)testCat.getCost());
    }

    @Test
    public void testSetCost() {
        CategoryItem testCat = new CategoryItem(id, name, 1.6F, date, dateSec);
        testCat.setCost(cost);
        Assert.assertEquals((int)cost, (int)testCat.getCost());
    }

    @Test
    public void testGetId() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(id, testCat.getId());
    }

    @Test
    public void testSetId() {
        CategoryItem testCat = new CategoryItem(10, name, cost, date, dateSec);
        testCat.setId(id);
        Assert.assertEquals(id, testCat.getId());
    }

    @Test
    public void testGetName() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(name, testCat.getName());
    }

    @Test
    public void testSetName() {
        CategoryItem testCat = new CategoryItem(id, "ARRA", cost, date, dateSec);
        testCat.setName(name);
        Assert.assertEquals(name, testCat.getName());
    }

    @Test
    public void testGetDate() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(date, testCat.getDate());
    }

    @Test
    public void testSetDate() {
        CategoryItem testCat = new CategoryItem(id, name, cost, "11.12.2021", dateSec);
        testCat.setDate(date);
        Assert.assertEquals(date, testCat.getDate());
    }

    @Test
    public void testGetDateSec() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(dateSec, testCat.getDateSec());
    }

    @Test
    public void testSetDateSec() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, 72);
        testCat.setDateSec(dateSec);
        Assert.assertEquals(dateSec, testCat.getDateSec());
    }
}