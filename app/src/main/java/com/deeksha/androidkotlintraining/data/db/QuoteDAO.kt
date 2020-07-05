package com.deeksha.androidkotlintraining.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deeksha.androidkotlintraining.data.entities.Quotes

@Dao
interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes: List<Quotes>)

    //We need not make this function suspending because

    @Query("SELECT * from QUOTES")
    fun getQuotes(): LiveData<List<Quotes>>
}