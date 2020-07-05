package com.deeksha.androidkotlintraining.ui.auth

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.deeksha.androidkotlintraining.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

    /*private lateinit var stringToBeTyped: String

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBeTyped = "KAS"
    }*/


    @Test
    fun login_isActivityInView() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.loginParentCL)).check(matches(isDisplayed()))
    }


    @Test
    fun login_areAllViewsOfLoginActivityVisible() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.emailEditText)).check(
            matches(withEffectiveVisibility(Visibility.VISIBLE))
        )
        onView(withId(R.id.passwordEditText)).check(matches(isDisplayed()))

        onView(withId(R.id.rememberMeCB)).check(matches(isDisplayed()))

        onView(withText(R.string.login_btn_label)).check(matches(isDisplayed()))
    }

    @Test
    fun login_isLoginTitleTextDisplayed() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.loginTitleLabel)).check(
            matches(withText(R.string.account_login_label))
        )
    }

    @Test
    fun login_emptyEmailID_LoginClicked_emptyEmailErrorVisible() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        // Type text and then press the button.
        onView(withId(R.id.emailEditText))
            .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.errorHeadingTV))
            .check(matches(isDisplayed()))

        onView(withId(R.id.errorHeadingTV))
            .check(matches(withText("Email is required")))
    }

    @Test
    fun login_wrongFormatEmailIDTyped_LoginClicked_EMAILFormatIncorrectErrorVisible() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        // Type text and then press the button.
        onView(withId(R.id.emailEditText))
            .perform(typeText("aks"), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.errorHeadingTV))
            .check(matches(isDisplayed()))

        onView(withId(R.id.errorHeadingTV))
            .check(matches(withText("Please Enter Correct Email")))
    }

    @Test
    fun login_emptyPassword_LoginClicked_EmptyPwdErrorVisible() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        // Type text and then press the button.
        onView(withId(R.id.emailEditText))
            .perform(typeText("aman@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.passwordEditText))
            .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.errorHeadingTV))
            .check(matches(isDisplayed()))

        onView(withId(R.id.errorHeadingTV))
            .check(matches(withText("Password is required")))
    }

    @Test
    fun login_allFieldsValid_loginClicked_navigateToHomeActivity() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        // Type text and then press the button.
        onView(withId(R.id.emailEditText))
            .perform(typeText("aman@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.passwordEditText))
            .perform(typeText("AMAN"), closeSoftKeyboard())
        onView(withId(R.id.loginBtn)).perform(click())

        onView(withId(R.id.home_drawer_layout)).check(matches(isDisplayed()))

    }
}