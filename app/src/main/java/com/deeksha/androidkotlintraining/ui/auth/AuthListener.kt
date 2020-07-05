package com.deeksha.androidkotlintraining.ui.auth

interface AuthListener {
    fun onSuccess(emailID: String)
    fun onFailure(message: String)
}