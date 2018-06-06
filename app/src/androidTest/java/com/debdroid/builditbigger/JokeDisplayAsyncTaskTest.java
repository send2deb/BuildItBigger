package com.debdroid.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class JokeDisplayAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void jokeDisplayAsyncTaskTest() {
        // Check that DisplayJoke activity is open when the asyncTask returns a joke
        // Find the joke tell button and perform click
        onView(withId(R.id.bt_tell_joke)).perform(click());
        // Check that the joke display text field is displayed
        onView(withId(R.id.tv_joke_data)).check(matches(isDisplayed()));
        // Also check that the joke header is displayed
        onView(withId(R.id.tv_joke_header)).check(matches(withText("Joke of the day")));
    }
}
