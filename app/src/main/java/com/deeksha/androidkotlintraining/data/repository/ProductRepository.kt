package com.deeksha.androidkotlintraining.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deeksha.androidkotlintraining.data.db.AppDataBase
import com.deeksha.androidkotlintraining.data.entities.Quotes
import com.deeksha.androidkotlintraining.data.network.MyApi
import com.deeksha.androidkotlintraining.data.network.SafeApiRequest
import com.deeksha.androidkotlintraining.data.preferences.PreferenceProvider
import com.deeksha.androidkotlintraining.utils.ApiException
import com.deeksha.androidkotlintraining.utils.Coroutines
import com.deeksha.androidkotlintraining.utils.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import javax.inject.Singleton


private const val MIN_INTERVAL = 6

@Singleton
class ProductRepository @Inject constructor(
    var apiInterface: MyApi,
    var db: AppDataBase,
    var preferenceProvider: PreferenceProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        //Since we are not inside activity or fragment so we do not worry about lifecycle changes
        quotes.observeForever {
            //Whenever there is any change in quotes we will push those quotes to our data base
            saveQuotes(it)
        }
    }

    //To save Quotes to our local DB
    private fun saveQuotes(quotes: List<Quotes>) {
        Coroutines.io {
            preferenceProvider.saveLastTimeStampToPreference(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

    //This function will be called from the View Model
    suspend fun getQuotes(): LiveData<List<Quotes>> {
        //In this method we will call fetchQuotesFromApi
        //If fetch is needed we get from APi otherwise we will get them from Local DB

        //we will use withContext because we need coroutine scope
        return withContext(Dispatchers.IO) {
            fetchQuotesFromAPI()
            db.getQuoteDao().getQuotes()
        }

    }

    //To Fetch Quotes from Backend API
    private suspend fun fetchQuotesFromAPI() {
        val lastSavedAt = preferenceProvider.getLastTimeStampFromPreference()
        //If we have copy of all the Quotes in our local DB we do not need to fetch from Server
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            //make the API request
            // Since we need to make API request so inherit SAfeApiRequest class which we created
            // so that we can call its apiRequest function

            try {
                val response = apiRequest {
                    apiInterface.getQuotes()
                }
                quotes.postValue(response.quotes)
                //Since we have Observer in init block as there is change these quotes wil be saved to local db
            } catch (e: ApiException) {

            } catch (e2: NoInternetException) {
                Log.e("No Internet", "NO INTERNET BLOCK")
            }
        }
    }

    private fun isFetchNeeded(lastSavedAt: LocalDateTime): Boolean {
        //We will check if local DB is more than 6 hours older then fetch from backend or if no data available
        //To do this we will store the timestamp when the data was saved in data base that is in SaveQuotes method
        return ChronoUnit.HOURS.between(lastSavedAt, LocalDateTime.now()) > MIN_INTERVAL
    }
}