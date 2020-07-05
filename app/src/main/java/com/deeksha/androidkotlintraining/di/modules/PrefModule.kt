package com.deeksha.androidkotlintraining.di.modules

import android.app.Application
import com.deeksha.androidkotlintraining.data.preferences.PreferenceProvider
import dagger.Module
import dagger.Provides

@Module
object PrefModule {

    @Provides
    internal fun providePreference(application: Application): PreferenceProvider {
        return PreferenceProvider(application)
    }

}
