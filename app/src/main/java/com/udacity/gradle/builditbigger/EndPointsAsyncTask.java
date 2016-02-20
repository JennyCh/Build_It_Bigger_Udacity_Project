package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.Jokes;
import com.example.jenny.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

public class EndPointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Jokes jokes;

    public EndPointsAsyncTask(){
        this.jokes = new Jokes();
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://hazel-core-122123.appspot.com/_ah/api/");
            // end options for devappserver
            myApiService = builder.build();
        }
        String returned;
        try {
            returned = myApiService.getJokeGceApi().execute().getData();
            return  returned;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
