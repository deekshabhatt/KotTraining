package com.deeksha.androidkotlintraining.ui.fragment

import androidx.lifecycle.ViewModel
import com.deeksha.androidkotlintraining.data.entities.Quotes
import com.deeksha.androidkotlintraining.data.repository.ProductRepository
import com.deeksha.androidkotlintraining.utils.lazyDeferred
import javax.inject.Inject

class ProductViewModel @Inject constructor(var quotesRepository: ProductRepository) : ViewModel() {
    lateinit var filteredList: List<Quotes>
    var serach: String? = ""
    val quotes by lazyDeferred {
        quotesRepository.getQuotes()
    }


    fun filterQuotes(searchText: String): List<Quotes> {
        this.serach = searchText
        val searchList = filteredList.filter {
            it.author!!.toString()
                .contains(searchText, ignoreCase = true)
        }
        return searchList
    }

}