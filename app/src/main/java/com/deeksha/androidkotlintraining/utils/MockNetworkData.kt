package com.deeksha.androidkotlintraining.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream

class MockNetworkData {
    fun loadJSONFromAsset(context: Context?): String? {
        var json: String?
        json = try {
            val inputStream: InputStream? = context?.assets?.open("QuotesData.json")
            val size: Int? = inputStream?.available()
            val buffer = size?.let { ByteArray(it)}
            inputStream?.read(buffer)
            inputStream?.close()
            buffer?.let { String(it) }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}