package com.debdroid.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    public static final String INTENT_JOKE_EXTRA = "intent_joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        String joke = getIntent().getStringExtra(INTENT_JOKE_EXTRA);

        TextView jokeTextView = findViewById(R.id.tv_joke_data);

        if(joke.isEmpty()) {
            jokeTextView.setText(getString(R.string.no_joke_msg));
        } else {
            jokeTextView.setText(joke);
        }
    }
}
