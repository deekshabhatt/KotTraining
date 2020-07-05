package com.deeksha.androidkotlintraining.ui.home.profile

import androidx.lifecycle.ViewModel
import com.deeksha.androidkotlintraining.data.repository.UserRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {
    val user = userRepository.getUser()
}
