package com.deeksha.androidkotlintraining.di.components

import android.app.Application
import com.deeksha.androidkotlintraining.di.modules.*
import com.deeksha.androidkotlintraining.ui.BaseApplication

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//
//LoginActivityBuilderModule::class,

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        RoomModule::class,
        PrefModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication?> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder?

        fun build(): AppComponent?

    }
}