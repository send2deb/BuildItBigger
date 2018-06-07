package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debdroid.jokedisplay.DisplayJoke;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener,
        JokeDisplayAsyncTask.CallbackForStartJokeActivity {
    private static final String TAG = "MainActivityFragment";
    
    private InterstitialAd mInterstitialAd;
    private Button tellJokeButton;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the MobAd
        MobileAds.initialize(getActivity(), getString(R.string.interstitial_ad_unit_id));

        // Create an interstitial ad object
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        // Load an ad
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        // Set the listener
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeTellingAsyncTask();
            }
        });
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
        // Show the Interstitial Ads if it's ready. otherwise show a toast
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial ad wasn't loaded yet.");
            Toast.makeText(getActivity(), "The interstitial ad wasn't loaded yet", Toast.LENGTH_SHORT).show();
            // Start the AsyncTask
            startJokeTellingAsyncTask();
        }
    }

    private void startJokeTellingAsyncTask() {
        // Kick-off the AsyncTask to retrieve and display a joke
        new JokeDisplayAsyncTask().execute(this);
        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Request a new ad if one isn't already loaded or for the next button click
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
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
