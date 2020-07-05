package com.deeksha.androidkotlintraining.ui

import com.deeksha.androidkotlintraining.di.components.AppComponent
import com.deeksha.androidkotlintraining.di.components.DaggerAppComponent
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder().application(this)?.build()
    }
}