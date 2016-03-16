package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;
import android.util.Pair;

/**
 * Created by Ujjwal Jain on 16-03-2016.
 */
public class StringResponseTest extends AndroidTestCase {

    private String LOG_TAG = StringResponseTest.class.getSimpleName();

    public void testNonNullResponse() {

        String joke = null;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(new Pair<Context, String>(getContext(), "Ujjwal"));

        try {
            joke = endpointsAsyncTask.get();
        } catch (Exception e) {
            Log.v(LOG_TAG, e.getMessage());
        }

        assertNotNull(joke);
    }
}
