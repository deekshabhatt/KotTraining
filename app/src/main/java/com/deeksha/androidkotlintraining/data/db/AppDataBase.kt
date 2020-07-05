package com.deeksha.androidkotlintraining.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deeksha.androidkotlintraining.data.entities.Quotes
import com.deeksha.androidkotlintraining.data.entities.User

@Database(
    entities = [User::class, Quotes::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDAO

    abstract fun getQuoteDao(): QuoteDAO
}