package com.deeksha.androidkotlintraining.ui.home.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.databinding.FragmentProfileBinding
import com.deeksha.androidkotlintraining.di.components.ViewModelFactory
import com.deeksha.androidkotlintraining.ui.home.HomeActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private val RECORD_REQUEST_CODE = 1

    private lateinit var profileViewModel: ProfileViewModel

    /* Each fragment will get a new factory instance */
    @Inject
    lateinit var vmFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        profileViewModel = ViewModelProvider(this, vmFactory).get(ProfileViewModel::class.java)

        //Create Binding Instance
        val bindingInstance: FragmentProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        bindingInstance.profileViewModelVar = profileViewModel

        //As we are binding live data to our layout we need to define lifecycle owner
        //So define current fragment as life cycle owner
        bindingInstance.lifecycleOwner = this


        return bindingInstance.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        voice_btn.setOnClickListener {
            setUpPermissions()
        }

    }

    private fun setUpPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            activity as HomeActivity,
            Manifest.permission.RECORD_AUDIO
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //  Log.i(TAG, "Permission to record denied")
            makeRequest()
        } else {
            speak()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            activity as HomeActivity,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            RECORD_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                } else {
                    speak()
                }
            }
        }
    }

    private fun speak() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi! Speak to search nearby cabs")
        try {
            startActivityForResult(intent, RECORD_REQUEST_CODE)
        } catch (e: Exception) {
            // Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    tvResult.text = result?.get(0)

                    findNavController().navigate(R.id.home_to_cab_action)
                }
            }
        }
    }


}