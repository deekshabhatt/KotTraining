package com.deeksha.androidkotlintraining.ui.fragment

import android.view.View
import com.deeksha.androidkotlintraining.data.entities.Quotes

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view: View, quote: Quotes)
}