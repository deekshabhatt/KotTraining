package com.deeksha.androidkotlintraining.di.modules

import androidx.lifecycle.ViewModelProvider
import com.deeksha.androidkotlintraining.di.components.ViewModelFactory

import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory?): ViewModelProvider.Factory?

}