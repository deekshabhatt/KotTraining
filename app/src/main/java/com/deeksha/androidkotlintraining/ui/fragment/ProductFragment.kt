package com.deeksha.androidkotlintraining.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.data.responses.QuotesResponse
import com.deeksha.androidkotlintraining.di.components.ViewModelFactory
import com.deeksha.androidkotlintraining.utils.Coroutines
import com.deeksha.androidkotlintraining.utils.MockNetworkData
import com.deeksha.androidkotlintraining.utils.toast
import com.google.gson.GsonBuilder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private val adapter = ProductAdapter()
    private lateinit var menuItem: MenuItem
    private lateinit var searchView: SearchView

    /* Each fragment will get a new factory instance */
    @Inject
    lateinit var vmFactory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        productViewModel = ViewModelProvider(this, vmFactory).get(ProductViewModel::class.java)
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view_authors.adapter = adapter

        //From View Model we can call the Quotes
        //Since the type of Quotes is Deferred<liveData<List<Quotes>>>
        //so we need to call await to get the LIveData from Quotes
        //Now we need to call the await from Coroutine scope or another suspend fun
        //We cant use suspend fun because we cant make onActivityCreated a suspending function
        //So therefore use Coroutine scope
        Coroutines.main {
            val quotesLiveData = productViewModel.quotes.await()
            //Await is used to call the lazyDeferred block which had async function Call
            quotesLiveData.observe(viewLifecycleOwner, Observer {
                context?.toast("Number of Quotes in API Response : ${it.size}")
                if (it.isEmpty()) {
                    val str = MockNetworkData().loadJSONFromAsset(context)
                    val gsonObj = GsonBuilder().create()
                    val quotesData = gsonObj.fromJson(str, QuotesResponse::class.java)
                    adapter.setAuthors(quotesData.quotes)
                } else {
                    productViewModel.filteredList = it
                    adapter.setAuthors(it.sortedBy { it.author })
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        menuItem = menu.findItem(R.id.menu_search)
        searchView = menuItem.actionView as SearchView
        if (productViewModel.serach!!.isNotEmpty()) {
            adapter.setAuthors(productViewModel.filterQuotes(productViewModel.serach!!))
            searchView.setIconified(false);
            searchView.setQuery(productViewModel.serach, false);

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    adapter.setAuthors(productViewModel.filterQuotes(newText))
                } else {
                    adapter.setAuthors(productViewModel.filteredList)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }
}