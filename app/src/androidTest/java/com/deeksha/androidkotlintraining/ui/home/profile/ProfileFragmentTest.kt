package com.deeksha.androidkotlintraining.ui.home.profile

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.deeksha.androidkotlintraining.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ProfileFragmentTest {

    @Test
    fun profile_isNameDisplayed() {
        //SETUP
        //val user = user1
        val scenario = launchFragmentInContainer<ProfileFragment>()
        onView(withId(R.id.welcomeLabelTV)).check(matches(isDisplayed()))
    }
}