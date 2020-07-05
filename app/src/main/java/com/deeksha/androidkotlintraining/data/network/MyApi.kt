package com.deeksha.androidkotlintraining.data.network

import com.deeksha.androidkotlintraining.data.responses.CabsResponse
import com.deeksha.androidkotlintraining.data.responses.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
   /* @GET("quotes")
    suspend fun getQuotes(): Response<QuotesResponse>*/

    @GET("17e63544-bf0c-4e4b-879d-fc91001d2846")
    suspend fun getCabList() : Response<CabsResponse>

    @GET("8db3f503-f61e-4b26-a0a5-21f453a366f2")
    suspend fun getQuotes() : Response<QuotesResponse>
}

