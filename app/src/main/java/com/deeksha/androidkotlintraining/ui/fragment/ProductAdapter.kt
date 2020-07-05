package com.deeksha.androidkotlintraining.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.data.entities.Quotes
import kotlinx.android.synthetic.main.item_quote.view.*


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.AuthorViewModel>() {

    private var authors = mutableListOf<Quotes>()
    var listener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AuthorViewModel(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote, parent, false)
    )

    override fun getItemCount() = authors.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AuthorViewModel, position: Int) {
      holder.view.quote.text = authors[position].quote
        holder.view.author.text = authors[position].author
    }

    fun setAuthors(authors: List<Quotes>) {
        this.authors = authors as MutableList<Quotes>
        notifyDataSetChanged()
    }


    class AuthorViewModel(val view: View) : RecyclerView.ViewHolder(view)
}