package com.deeksha.androidkotlintraining.data.responses

import com.deeksha.androidkotlintraining.data.entities.Cab


data class CabsResponse (
    val isSuccessful : Boolean,
    val res: List<Cab>
)