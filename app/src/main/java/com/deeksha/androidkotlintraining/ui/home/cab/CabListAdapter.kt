package com.deeksha.androidkotlintraining.ui.home.cab

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.data.entities.Cab
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cab.view.*


class CabListAdapter : RecyclerView.Adapter<CabListAdapter.SearchViewModel>() {

    private var cabList = mutableListOf<Cab>()
    var onItemClick: ((Cab) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cab, parent, false)
        )

    override fun getItemCount() = cabList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchViewModel, position: Int) {
        holder.view.name.text = cabList[position].vehicleDetails!!.name
        holder.view.model_name.text = cabList[position].modelName
        holder.view.license_plate.text = cabList[position].licensePlate
        Picasso
            .get() // give it the context
            .load(cabList[position].carImageUrl) // load the image
            .into(holder.view.iv_cab)
        holder.view.iv_location.setOnClickListener()
        {
            onItemClick?.invoke(cabList[position])

        }
    }

    fun setCabs(cabs: List<Cab>) {
        this.cabList = cabs as MutableList<Cab>
        notifyDataSetChanged()
    }


    class SearchViewModel(val view: View) : RecyclerView.ViewHolder(view)
}