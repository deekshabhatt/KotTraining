package com.deeksha.androidkotlintraining.ui

import com.deeksha.androidkotlintraining.ui.auth.LoginActivityTest
import com.deeksha.androidkotlintraining.ui.home.HomeActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginActivityTest::class,
    HomeActivityTest::class
)
class ActivityTestSuite