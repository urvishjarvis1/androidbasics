package com.example.urvish.assignment7;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {
    @Rule
    public ActivityTestRule<RetrofitActivity> rule=new ActivityTestRule<>(RetrofitActivity.class,true,false);
    @Test
    public void activity(){
        Intent i=new Intent();
        rule.launchActivity(i);
        onView(withId(R.id.btn_postdata)).perform(click());
        onView(withId(R.id.edittxtname)).check(matches(withText("")));
    }
    @Test
    public void databaseTest(){
        Intent i=new Intent();
        rule.launchActivity(i);
        onView(withId(R.id.edittxtname)).perform(clearText(),typeText("urvish"));
        onView(withId(R.id.edittxtemail)).perform(clearText(),typeText("urvish@123.com"));
        onView(withId(R.id.btn_postdata)).perform(click());
        onView(withId(R.id.txt_data)).check(matches(withText("there could be some problem with service")));
    }
}
