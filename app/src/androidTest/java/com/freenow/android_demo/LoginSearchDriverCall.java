package com.freenow.android_demo;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 * Author: Yuri Valverde
 * This the fullcase for login , then search for driver and press call the driver.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginSearchDriverCall {
    //constants for username and password
    private final static String USERNAME="crazydog335";
    private final static String PASSWORD="venture";

    @Rule
    public ActivityTestRule<AuthenticationActivity> authActivityRule = new ActivityTestRule<AuthenticationActivity>(AuthenticationActivity.class,true,false){
        @Override
        protected Intent getActivityIntent(){
           Intent intent = new Intent ();
           intent.putExtra("key","value");
           return intent;
        }
    };
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    //Enable locations/gps for the app
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Test
    public void LoginSearchDisplayUserCall() throws Exception{

        loginUsers();//Call login  testing function
        searchDriver(); //Call search driver testing function
    }


    public void loginUsers(){
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

            Log.println(Log.INFO, "@Test", "*******User " + USERNAME + " successfully logged in*******");
        } catch (Exception e){
            Log.println(Log.INFO, "@Test", "Exception " + e);
        }
    }


    @Test
    public void searchDriver() throws Exception {
        //launch the main screen
        mActivityRule.launchActivity(null);
        String driver = "Sarah Scott";

        Log.println(Log.INFO, "@Test", "*****Test Starts for search a driver*****");

        onView(withId(R.id.textSearch)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.textSearch)).perform(typeText("s"));
        Thread.sleep(450); //give time to the dropdown logic to come
        onView(withId(R.id.textSearch)).perform(typeText("a"));
        // Check for the name of the driver to be available before selecting from the list.
        onView(withText(driver))
                .inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));
        onView(withText(driver))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
        //make sure button to call is displayed before clicking it
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());

        Log.println(Log.INFO, "@Test", "*****Will call " + driver + " driver******");
    }
}
