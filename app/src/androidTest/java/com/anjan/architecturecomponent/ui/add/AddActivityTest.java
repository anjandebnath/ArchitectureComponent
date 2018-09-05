package com.anjan.architecturecomponent.ui.add;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.anjan.architecturecomponent.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Anjan Debnath on 9/5/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class AddActivityTest {

    private String mMovieName;
    private String mDirectorName;

    @Rule
    public ActivityTestRule<AddActivity> mActivityRule =
            new ActivityTestRule<>(AddActivity.class);

    @Before
    public void setup() throws Exception {
        mMovieName = "Titanic";
        mDirectorName = "James Camerun";
    }

    @Test
    public void addEntry_save_success() {


        onView(withId(R.id.editText_movie))
                .perform(clearText(),typeText(mMovieName))
                 .check(matches(isDisplayed()));

        closeSoftKeyboard();

        onView(withId(R.id.editText_director))
                .perform(clearText(),typeText(mDirectorName));

        closeSoftKeyboard();

        onView(withId(R.id.button_save)).perform(click());

    }
}