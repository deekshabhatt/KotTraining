package com.deeksha.androidkotlintraining.di.modules

import androidx.lifecycle.ViewModel
import com.deeksha.androidkotlintraining.di.components.ViewModelKey
import com.deeksha.androidkotlintraining.ui.auth.LoginViewModel
import com.deeksha.androidkotlintraining.ui.fragment.ProductViewModel
import com.deeksha.androidkotlintraining.ui.home.cab.CabViewModel
import com.deeksha.androidkotlintraining.ui.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViewModel(viewModel: ProductViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(CabViewModel::class)
    abstract fun bindCabViewModel(viewModel: CabViewModel?): ViewModel?
}