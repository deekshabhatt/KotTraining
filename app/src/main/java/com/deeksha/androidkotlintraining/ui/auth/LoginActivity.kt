package com.deeksha.androidkotlintraining.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.databinding.ActivitySignInBinding
import com.deeksha.androidkotlintraining.di.components.ViewModelFactory
import com.deeksha.androidkotlintraining.ui.home.HomeActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), AuthListener {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var binding: ActivitySignInBinding

    @Inject
    lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.lifecycleOwner = this
        val loginViewModel = ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)

        if (loginViewModel.getRememberMeStatus()) {
            loginViewModel.getLoggedInUser().observe(this, Observer { user ->
                if (user != null) {
                    binding.emailEditText.setText(user.email!!.toString())
                    binding.passwordEditText.setText(user.password!!.toString())
                    binding.rememberMeCB.isChecked = loginViewModel.getRememberMeStatus()

                    //if USER EXISTS IN PREF We will pre fill the data
                    /* Intent(this, HomeActivity::class.java).also {
                         it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                         startActivity(it)
                     }*/
                }
            })
        }


        binding.loginViewModel = loginViewModel

        loginViewModel.authListener = this

        firebaseAnalytics = Firebase.analytics


    }

    override fun onSuccess(emailID: String) {
        binding.errorHeadingTV.visibility = View.GONE
        Toast.makeText(
            this@LoginActivity,
            " $emailID you have Logged in successfully",
            Toast.LENGTH_SHORT
        )
            .show()
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param("EMAIL_OF_LOGGED_IN_USER", emailID)
            param(FirebaseAnalytics.Param.ITEM_NAME, "Login was Clicked")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "LoginString")
        }

        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        binding.errorHeadingTV.text = message
        binding.errorHeadingTV.visibility = View.VISIBLE
        val bundle = Bundle()
        bundle.putString("Login_Failure", message)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }
}