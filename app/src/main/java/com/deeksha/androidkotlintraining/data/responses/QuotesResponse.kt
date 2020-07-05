package com.deeksha.androidkotlintraining.data.responses

import com.deeksha.androidkotlintraining.data.entities.Quotes

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quotes>
)