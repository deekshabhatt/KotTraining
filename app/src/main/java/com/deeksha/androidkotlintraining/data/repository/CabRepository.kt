package com.deeksha.androidkotlintraining.data.repository

import com.deeksha.androidkotlintraining.data.network.MyApi
import com.deeksha.androidkotlintraining.data.responses.CabsResponse
import retrofit2.Response
import javax.inject.Inject

class CabRepository @Inject constructor(
    var apiInterface: MyApi
) {
    suspend fun fetchCabResults(): Response<CabsResponse> {
        return apiInterface.getCabList()
    }

}