package com.tdr.app.bakingapp;

import android.widget.TextView;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

    public static final String RECIPE_NAME = "Brownies";
    public static final String SERVINGS_COUNT = "8";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void clickRecyclerView_OpensRecipeDetailsActivity() {

        onView(withId(R.id.recipe_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText(RECIPE_NAME)));

        onView(withId(R.id.servings_text_view)).check(matches(withText(SERVINGS_COUNT)));


    }


}
