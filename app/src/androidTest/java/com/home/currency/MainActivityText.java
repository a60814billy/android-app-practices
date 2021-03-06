package com.home.currency;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityText {

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void empty_ntd() {
        onView(withId(R.id.btn_go)).perform(click());
        onView(withText("Problem")).check(matches(isDisplayed()));
        onView(withText("Please enter your NTD amount")).check(matches(isDisplayed()));
    }

    @Test
    public void can_show_result () {
        onView(withId(R.id.ntd)).perform(click()).perform(typeText("10000"));
        onView(withId(R.id.btn_go)).perform(click());
        onView(withText("Result")).check(matches(isDisplayed()));

        String result = "USD is 323.6246\nJPY is 37037.035";
        onView(withText(result)).check(matches(isDisplayed()));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.us)).check(matches(withText("323.624603")));
        onView(withId(R.id.jp)).check(matches(withText("37037.035156")));
    }

}
