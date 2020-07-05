package com.deeksha.androidkotlintraining.di.components


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

//@Singleton
class ViewModelFactory @Inject constructor(
    creators: MutableMap<Class<out ViewModel?>?, @JvmSuppressWildcards Provider<ViewModel?>?>?
) : ViewModelProvider.Factory {
    private val creators: MutableMap<Class<out ViewModel?>?, Provider<ViewModel?>?>?

    init {
        this.creators = creators
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel?>? = creators!![modelClass]
        if (creator == null) {
            for (entry in creators.entries) { // if it's allowed, set the Provider<ViewModel>
                if (modelClass!!.isAssignableFrom(entry.key)) {
                    creator = entry.value
                    break
                }
            }
        }
        // if this is not one of the allowed keys, throw exception
        requireNotNull(creator) { "unknown model class $modelClass" }
        // return the Provider
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}


