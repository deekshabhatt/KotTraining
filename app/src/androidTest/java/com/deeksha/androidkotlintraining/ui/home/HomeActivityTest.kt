package com.deeksha.androidkotlintraining.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.deeksha.androidkotlintraining.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {
    @get: Rule
    val activityScenarioRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun home_isActivityInView() {
        onView(withId(R.id.home_drawer_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun home_areAllViewsOfProfileFragment_VISIBLE() {
        onView(withId(R.id.profileLayoutLL)).check(matches(isDisplayed()))
        onView(withId(R.id.welcomeLabelTV)).check(matches(isDisplayed()))
    }

    @Test
    fun home_welcomeLabelOnProfileFragmentIsDisplayed() {
        onView(withId(R.id.welcomeLabelTV)).check(matches(withText(R.string.welcome)))
    }
}