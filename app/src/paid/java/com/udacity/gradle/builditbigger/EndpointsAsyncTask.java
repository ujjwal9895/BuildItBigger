package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.backend.jokeApi.JokeApi;
import com.example.ujjwaljain.jokedisplay.LibraryActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokes.JokeTelling;

import java.io.IOException;
import java.lang.Override;

/**
 * Created by Ujjwal Jain on 15-03-2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static JokeApi myApiService = null;
    private Context context;
    private ProgressBar spinner;

    public EndpointsAsyncTask(Context c, ProgressBar progressBar) {
        this.context = c;
        this.spinner = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (spinner != null) {
            spinner.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

        if (myApiService == null) {

            JokeApi.Builder builder = new JokeApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null)
                    .setRootUrl("https://builditbigger-1251.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        JokeTelling jokeTelling = new JokeTelling();
        String joke = jokeTelling.getJoke();

        try {
            return myApiService.fetchJoke(joke).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);

        if (spinner != null)
            spinner.setVisibility(View.GONE);
        Intent intent = new Intent(context, LibraryActivity.class);
        intent.putExtra("joke", s);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}
