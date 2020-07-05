package com.deeksha.androidkotlintraining.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deeksha.androidkotlintraining.data.entities.CURRENT_USER_ID
import com.deeksha.androidkotlintraining.data.entities.User

@Dao
interface UserDAO {
    //To insert or update
    //In case of conflict we will replace the current user with already saved one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * from USER WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}