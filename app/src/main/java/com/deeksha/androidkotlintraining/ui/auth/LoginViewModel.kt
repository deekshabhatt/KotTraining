package com.deeksha.androidkotlintraining.ui.auth

import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.ViewModel
import com.deeksha.androidkotlintraining.data.dummydata.DummyUser.user1
import com.deeksha.androidkotlintraining.data.entities.User
import com.deeksha.androidkotlintraining.data.repository.UserRepository
import com.deeksha.androidkotlintraining.utils.Coroutines
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var userRepository: UserRepository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    var errorMsg: String? = null

    private var isRememberMeChecked: Boolean = false

    fun getLoggedInUser() = userRepository.getUser()

    fun getRememberMeStatus() = userRepository.getRememberMeStatus()

    fun onLoginButtonClick(view: View) {
        if (email.isNullOrEmpty()) {
//            errorMsg = applica
            errorMsg = "Email is required"
            authListener?.onFailure(errorMsg!!)
            return
        }
        val emailCharSequence: CharSequence = email!!
        if (!Patterns.EMAIL_ADDRESS.matcher(emailCharSequence).matches()) {
            errorMsg = "Please Enter Correct Email"
            authListener?.onFailure(errorMsg!!)
            return
        }
        if (password.isNullOrEmpty()) {
            errorMsg = "Password is required"
            authListener?.onFailure(errorMsg!!)
            return
        }
        errorMsg = null
        Coroutines.main {
            user1 = User(
                538,
                "AMAN",
                email,
                password,
                "2020-06-18 16:12:57",
                "2020-06-18 16:12:57"
            )
            userRepository.saveUser(
                user1
            )
        }
        Log.e("REMEMBER", "status is $isRememberMeChecked")
        userRepository.saveRememberMeStatus(isRememberMeChecked)
        authListener?.onSuccess(email.toString())

    }

    fun onCheckedChange(button: CompoundButton?, isChecked: Boolean) {
        isRememberMeChecked = isChecked
    }
}