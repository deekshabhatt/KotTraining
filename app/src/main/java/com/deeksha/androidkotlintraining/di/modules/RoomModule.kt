package com.deeksha.androidkotlintraining.di.modules

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.deeksha.androidkotlintraining.data.db.AppDataBase
import com.deeksha.androidkotlintraining.data.db.QuoteDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule() {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDataBase {
        return Room
            .databaseBuilder(application, AppDataBase::class.java, "Quotes.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideQuotesDao(@NonNull database: AppDataBase): QuoteDAO {
        return database.getQuoteDao()
    }

}