package com.example.anotherversion.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Category item test.
 */
public class CategoryItemTest {
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
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        id = 3;
        name = "Anton";
        cost = 12.3F;
        date = "12.12.2012";
        dateSec = 246257;
    }

    /**
     * Test get cost.
     */
    @Test
    public void testGetCost() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals((int)cost, (int)testCat.getCost());
    }

    /**
     * Test set cost.
     */
    @Test
    public void testSetCost() {
        CategoryItem testCat = new CategoryItem(id, name, 1.6F, date, dateSec);
        testCat.setCost(cost);
        Assert.assertEquals((int)cost, (int)testCat.getCost());
    }

    /**
     * Test get id.
     */
    @Test
    public void testGetId() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(id, testCat.getId());
    }

    /**
     * Test set id.
     */
    @Test
    public void testSetId() {
        CategoryItem testCat = new CategoryItem(10, name, cost, date, dateSec);
        testCat.setId(id);
        Assert.assertEquals(id, testCat.getId());
    }

    /**
     * Test get name.
     */
    @Test
    public void testGetName() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(name, testCat.getName());
    }

    /**
     * Test set name.
     */
    @Test
    public void testSetName() {
        CategoryItem testCat = new CategoryItem(id, "ARRA", cost, date, dateSec);
        testCat.setName(name);
        Assert.assertEquals(name, testCat.getName());
    }

    /**
     * Test get date.
     */
    @Test
    public void testGetDate() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(date, testCat.getDate());
    }

    /**
     * Test set date.
     */
    @Test
    public void testSetDate() {
        CategoryItem testCat = new CategoryItem(id, name, cost, "11.12.2021", dateSec);
        testCat.setDate(date);
        Assert.assertEquals(date, testCat.getDate());
    }

    /**
     * Test get date sec.
     */
    @Test
    public void testGetDateSec() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, dateSec);
        Assert.assertEquals(dateSec, testCat.getDateSec());
    }

    /**
     * Test set date sec.
     */
    @Test
    public void testSetDateSec() {
        CategoryItem testCat = new CategoryItem(id, name, cost, date, 72);
        testCat.setDateSec(dateSec);
        Assert.assertEquals(dateSec, testCat.getDateSec());
    }
}