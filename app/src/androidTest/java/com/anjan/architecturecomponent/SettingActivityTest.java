package com.anjan.architecturecomponent;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.PreferenceMatchers.withKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class SettingActivityTest {

    SharedPreferences.Editor preferencesEditor;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            SettingActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

    }


    @Test
    public void clickListPreference() throws Exception{

        // Check if it is displayed
        Context appContext = InstrumentationRegistry.getTargetContext();

        onData(allOf(
                is(instanceOf(Preference.class)),
                withKey(appContext.getResources().getString(R.string.pref_list))))
                .check(matches(isDisplayed()));


        onData(allOf(
                is(instanceOf(Preference.class)),
                withKey(appContext.getResources().getString(R.string.pref_list))))
                .onChildView(withText(appContext.getResources().getString(R.string.pref_list_title)))
                .perform(click());


    }

}