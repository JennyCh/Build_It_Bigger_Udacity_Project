package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {

       public void testGetNonEmptyJoke1() throws Exception{
        try {
            EndPointsAsyncTask task = new EndPointsAsyncTask();
            task.execute();
            String returnedJoke = task.get(30, TimeUnit.SECONDS);
            assertTrue(returnedJoke.length() > 0 );
        }catch(Exception e){
            Log.v("Test Failed", e.getMessage());
            fail("timed out");
        }
    }
}