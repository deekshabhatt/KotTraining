package com.deeksha.androidkotlintraining.di.modules

import com.deeksha.androidkotlintraining.ui.auth.LoginActivity
import com.deeksha.androidkotlintraining.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class,ViewModelFactoryModule::class])
    abstract fun contributeProductActivity(): HomeActivity?

    @ContributesAndroidInjector(modules = [ViewModelModule::class,ViewModelFactoryModule::class])
    abstract fun contributeLoginActivity(): LoginActivity?

}