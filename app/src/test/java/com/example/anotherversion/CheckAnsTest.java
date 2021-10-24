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
@RunWith(RobolectricTestRunner.class)
public class CheckAnsTest {
    private CheckAns activity;

    @Before
    public void setActivity() throws Exception {
        activity = Robolectric.buildActivity(CheckAns.class)
                .create()
                .resume()
                .get();
    }
    @Test
    public void activityShouldNotBeNull(){
        Assert.assertNotNull(activity); }
}