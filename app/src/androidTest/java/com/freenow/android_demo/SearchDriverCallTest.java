package com.freenow.android_demo;

import android.os.SystemClock;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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

@RunWith(AndroidJUnit4.class)
public class SearchDriverCallTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void searchDriver() throws Exception {

        /*String[] driver = {"S", "a", "r", "a","h"};*/
        String driver = "sa";
        /*if (0 != driver.length()) {
            onView(withId(R.id.textSearch)).perform(typeText(driver.substring(0,1)));
            for(int i= 1; i < driver.length(); i++) {
                onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView(driver.substring(i,i+1)));
            }
        }*/
        onView(withId(R.id.textSearch)).perform(typeText("s"),closeSoftKeyboard());
        SystemClock.sleep(150);
        onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView("a"), closeSoftKeyboard());
        /*onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));*/
        onView(withText("Sarah Scott"))
                .inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));

        onView(withText("Sarah Scott"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());

        /*onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());*/

        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());

    }

}
