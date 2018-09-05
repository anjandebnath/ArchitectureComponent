package com.anjan.architecturecomponent.ui.main;

import android.support.constraint.ConstraintLayout;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.anjan.architecturecomponent.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by Anjan Debnath on 9/5/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private ConstraintLayout mConstraintLayout;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);



    @Before
    public void setup() throws Exception {
        mConstraintLayout = mActivityRule.getActivity().findViewById(R.id.root);
    }

    @Test
    public void click_floatingButton_success() {

        onView(withId(R.id.floatingActionButton4))
                .perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Test Success")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void click_recyclerView_success() {
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }


}