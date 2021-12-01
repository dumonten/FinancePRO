package com.example.anotherversion.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Category test.
 */
public class CategoryTest {
    /**
     * The Test id.
     */
    int testId;
    /**
     * The Test name.
     */
    String testName;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        testId = 2;
        testName = "Repo";
    }

    /**
     * Test get id.
     */
    @Test
    public void testGetId() {
        Category testCat = new Category(testId, testName);
        Assert.assertEquals(testId, testCat.getId());
    }

    /**
     * Test set id.
     */
    @Test
    public void testSetId() {
        Category testCat = new Category(1, testName);
        testCat.setId(testId);
        Assert.assertEquals(testId, testCat.getId());
    }

    /**
     * Test get name.
     */
    @Test
    public void testGetName() {
        Category testCat = new Category(testId, testName);
        Assert.assertEquals(testName, testCat.getName());
    }

    /**
     * Test set name.
     */
    @Test
    public void testSetName() {
        Category testCat = new Category(testId, "Rubi");
        testCat.setName(testName);
        Assert.assertEquals(testName, testCat.getName());
    }
}