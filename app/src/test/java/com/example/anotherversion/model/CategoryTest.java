package com.example.anotherversion.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
    int testId;
    String testName;

    @Before
    public void setUp() throws Exception {
        testId = 2;
        testName = "Repo";
    }

    @Test
    public void testGetId() {
        Category testCat = new Category(testId, testName);
        Assert.assertEquals(testId, testCat.getId());
    }

    @Test
    public void testSetId() {
        Category testCat = new Category(1, testName);
        testCat.setId(testId);
        Assert.assertEquals(testId, testCat.getId());
    }

    @Test
    public void testGetName() {
        Category testCat = new Category(testId, testName);
        Assert.assertEquals(testName, testCat.getName());
    }

    @Test
    public void testSetName() {
        Category testCat = new Category(testId, "Rubi");
        testCat.setName(testName);
        Assert.assertEquals(testName, testCat.getName());
    }
}