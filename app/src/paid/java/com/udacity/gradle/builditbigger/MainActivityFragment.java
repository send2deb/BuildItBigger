package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debdroid.jokedisplay.DisplayJoke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener,
        JokeDisplayAsyncTask.CallbackForStartJokeActivity {

    private Button tellJokeButton;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = root.findViewById(R.id.progress_bar);
        tellJokeButton = root.findViewById(R.id.bt_tell_joke);
        tellJokeButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        // Kick-off the AsyncTask to retrieve and display a joke
        new JokeDisplayAsyncTask().execute(this);
        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void startJokeDisplayActivity(String result) {
        progressBar.setVisibility(View.GONE);
        if(result == null || result.isEmpty()) {
            Toast.makeText(getActivity(),getString(R.string.no_joke_msg), Toast.LENGTH_SHORT).show();
        } else {
            Intent displayJokeIntent = new Intent(getActivity(), DisplayJoke.class);
            displayJokeIntent.putExtra(DisplayJoke.INTENT_JOKE_EXTRA, result);
            startActivity(displayJokeIntent);
        }
    }
}
