package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debdroid.jokedisplay.DisplayJoke;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeDisplayAsyncTask extends AsyncTask<Pair<Context, ProgressBar>, Void, String> {
    private static final String TAG = "JokeDisplayAsyncTask";
    private static MyApi myApiService = null;
    private Context mContext;
    private ProgressBar mProgressBar;

    @Override
    protected String doInBackground(Pair<Context, ProgressBar>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();
        }

        mContext = params[0].first;
        mProgressBar = params[0].second;

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mProgressBar.setVisibility(View.GONE);
        if(result == null || result.isEmpty()) {
            Toast.makeText(mContext, mContext.getString(R.string.no_joke_msg), Toast.LENGTH_SHORT).show();
        } else {
            Intent displayJokeIntent = new Intent(mContext, DisplayJoke.class);
            displayJokeIntent.putExtra(DisplayJoke.INTENT_JOKE_EXTRA, result);
            mContext.startActivity(displayJokeIntent);
        }
    }
}
