package com.deeksha.androidkotlintraining.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}
