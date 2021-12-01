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
import  com.example.anotherversion.MainActivity;
import org.testng.Assert;

import static org.junit.Assert.assertEquals;

/**
 * The type Main activity test.
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    /**
     * Sets activity.
     *
     * @throws Exception the exception
     */
    @Before
    public void setActivity() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
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