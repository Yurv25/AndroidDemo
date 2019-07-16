package com.freenow.android_demo;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.freenow.android_demo.activities.AuthenticationActivity;
import com.freenow.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/*
Testing just the login functionality.
 */
@RunWith(AndroidJUnit4.class)
public class LoginUserTest {

    //constants for username and password
    private final static String USERNAME="crazydog335";
    private final static String PASSWORD="venture";

    @Rule
    public ActivityTestRule<AuthenticationActivity> authActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);

    @Test
    public void LoginUser(){
        try {
            //launch login page
            authActivityRule.launchActivity(new Intent());
            Log.println(Log.INFO, "@Test", "*****Login starts*****");
            //Check both username and password fields are displayed
            onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
            onView(withId(R.id.edt_password)).check(matches(isDisplayed()));

            onView(withId(R.id.edt_username)).perform(typeText(USERNAME));
            onView(withId(R.id.edt_password)).perform(typeText(PASSWORD), closeSoftKeyboard());
            onView(withId(R.id.btn_login)).perform(click());

            Log.println(Log.INFO, "@Test", "*******User " + USERNAME + " successfully logged in*********");
        }
        catch (Exception e){
            Log.println(Log.INFO,"@Test","Exception " + e);
        }
    }

}
