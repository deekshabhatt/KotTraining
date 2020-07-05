package com.deeksha.androidkotlintraining.di.modules

import com.deeksha.androidkotlintraining.data.db.AppDataBase
import com.deeksha.androidkotlintraining.data.network.MyApi
import com.deeksha.androidkotlintraining.data.preferences.PreferenceProvider
import com.deeksha.androidkotlintraining.data.repository.CabRepository
import com.deeksha.androidkotlintraining.data.repository.ProductRepository
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideQuotesRepo(api: MyApi,appDataBase: AppDataBase,preferenceProvider: PreferenceProvider): ProductRepository {
        return ProductRepository(api,appDataBase,preferenceProvider)
    }

    @Provides
    fun provideCabRepo(api: MyApi): CabRepository {
        return CabRepository(api)
    }

}