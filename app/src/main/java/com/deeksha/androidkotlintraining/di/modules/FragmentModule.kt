package com.deeksha.androidkotlintraining.di.modules

import com.deeksha.androidkotlintraining.ui.fragment.ProductFragment
import com.deeksha.androidkotlintraining.ui.home.cab.CabListFragment
import com.deeksha.androidkotlintraining.ui.home.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindProductFragment(): ProductFragment?

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindProfileFragment(): ProfileFragment?

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindCabFragment(): CabListFragment?

}