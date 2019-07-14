package com.freenow.android_demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Rule
    public ActivityTestRule<AuthenticationActivity> authActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.freenow.android_demo", appContext.getPackageName());
    }


    @Test
    public void LoginSearchDisplayUserCall() throws Exception{

        loginUsers();
        SearchDriverCallTest cs = new SearchDriverCallTest();
        cs.searchDriver();
    }

    @Test
    public void loginUsers() throws Exception{

        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_username)).perform(typeText("crazydog335"), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText("venture"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

    }




    /*@Test
    public void searchDriver() throws Exception {


        String driver = "sa";
        if (0 != driver.length()) {
            onView(withId(R.id.textSearch)).perform(typeText(driver.substring(0,1)));
            for(int i= 1; i < driver.length(); i++) {
                onView(withId(R.id.textSearch)).perform(typeTextIntoFocusedView(driver.substring(i,i+1)));
            }
        }*/
        //onView(withId(R.id.textSearch)).perform(typeText("s"),closeSoftKeyboard());

        //SystemClock.sleep(500);
        //onView(withId(R.id.textSearch)).perform(typeText("a"), closeSoftKeyboard());
        /*onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));*/
        /*onView(withText("Sarah Scott"))
                .inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));

        onView(withText("Sarah Scott"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());*/

        /*onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());*/
        /*
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());

    }*/

}
