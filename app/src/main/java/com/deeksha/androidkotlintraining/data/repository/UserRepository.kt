package com.deeksha.androidkotlintraining.data.repository

import com.deeksha.androidkotlintraining.data.db.AppDataBase
import com.deeksha.androidkotlintraining.data.entities.User
import com.deeksha.androidkotlintraining.data.preferences.PreferenceProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    var db: AppDataBase,
    var preferenceProvider: PreferenceProvider
) {

    suspend fun saveUser(user: User) = db.getUserDao().insert(user)

    fun getUser() = db.getUserDao().getUser()

    //To save Quotes to our local DB
    fun saveRememberMeStatus(isRememberMeChecked: Boolean) {
        preferenceProvider.saveRememberMeToPreference(isRememberMeChecked)
    }

    fun getRememberMeStatus(): Boolean {
        return preferenceProvider.getRememberMeStatus()
    }
}