package com.deeksha.androidkotlintraining.ui.home.cab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.di.components.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cabs.*
import javax.inject.Inject


class CabListFragment : Fragment() {

    private lateinit var viewModel: CabViewModel
    private val adapter =
        CabListAdapter()

    @Inject
    lateinit var vmFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(this,vmFactory).get(CabViewModel::class.java)
        return inflater.inflate(R.layout.fragment_cabs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_cabs.adapter = adapter
        adapter.onItemClick = { cab ->

            var action = bundleOf("CAB" to cab)
            findNavController().navigate(R.id.location_to_map, action)
        }
        viewModel.fetchData()
        viewModel.cabLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setCabs(it)
        })
    }
}