package com.example.anotherversion;

import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import  com.example.anotherversion.EnterPassword;
import org.testng.Assert;

import static org.junit.Assert.assertEquals;

/**
 * The type Enter password test.
 */
@RunWith(RobolectricTestRunner.class)
public class EnterPasswordTest{
    private EnterPassword activity;

    /**
     * Set activity.
     */
    @Before
    public void setActivity(){
        activity = Robolectric.buildActivity(EnterPassword.class)
                .create()
                .resume()
                .get();
    }

    /**
     * Activity should not be null.
     */
    @Test
    public void activityShouldNotBeNull(){
        Assert.assertNotNull(activity); }
}