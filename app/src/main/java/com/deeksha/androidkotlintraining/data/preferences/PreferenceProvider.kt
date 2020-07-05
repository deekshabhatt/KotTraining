package com.deeksha.androidkotlintraining.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import javax.inject.Inject

private const val KEY_QUOTES_SAVED_AT = "quotes_saved_at"
private const val KEY_REMEMBER_ME = "remember_me"

class PreferenceProvider @Inject constructor(var context: Context) {
    //Even if we pass fragment context or activity context we will get the whole application context
    //Which will prevent the memory leaks
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveRememberMeToPreference(isRememberMeChecked: Boolean) {
        preference.edit().putBoolean(KEY_REMEMBER_ME, isRememberMeChecked).apply()
    }

    fun getRememberMeStatus(): Boolean {
        return preference.getBoolean(KEY_REMEMBER_ME, false)
    }

    fun saveLastTimeStampToPreference(savedAt: String) {
        preference.edit().putString(KEY_QUOTES_SAVED_AT, savedAt).apply()
    }

    fun getLastTimeStampFromPreference(): String? {
        return preference.getString(KEY_QUOTES_SAVED_AT, null)
    }
}