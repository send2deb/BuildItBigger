package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

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
        new JokeDisplayAsyncTask().execute(new Pair<Context, ProgressBar>(getActivity(), progressBar));
        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);
    }
}
