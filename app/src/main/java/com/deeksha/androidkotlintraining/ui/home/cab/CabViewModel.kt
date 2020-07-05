package com.deeksha.androidkotlintraining.ui.home.cab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deeksha.androidkotlintraining.data.entities.Cab
import com.deeksha.androidkotlintraining.data.repository.CabRepository
import com.deeksha.androidkotlintraining.utils.Coroutines
import javax.inject.Inject

class CabViewModel @Inject constructor(var cabRepository: CabRepository) : ViewModel() {


    private val _cab = MutableLiveData<List<Cab>>()
    val cabLiveData: LiveData<List<Cab>>
        get() = _cab


    fun fetchData() {
        Coroutines.main {
            val response = cabRepository.fetchCabResults()
            if (response.isSuccessful) {
                _cab.value = response.body()?.res
            }
        }

    }
}