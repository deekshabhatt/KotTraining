package com.deeksha.androidkotlintraining.ui.home.cab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deeksha.androidkotlintraining.R
import com.deeksha.androidkotlintraining.data.entities.Cab
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_google_map.*

class GoogleMapFragment  : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var cab: Cab
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_google_map, container, false)
    }

   
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cab = arguments?.getParcelable<Cab>("CAB")!!

        map.onCreate(savedInstanceState)
        map.onResume()
        map.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            mMap = it
            var lat: Double = cab.location?.latitude?.toDouble()!!
            var lng: Double = cab.location?.longitude?.toDouble()!!
            val PERTH = LatLng(lat, lng)
            mMap.animateCamera(CameraUpdateFactory.newLatLng(PERTH))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH, 10F))
            mMap.addMarker(
                       MarkerOptions()
                           .position(PERTH)
                           .title(cab.vehicleDetails?.name + " "+cab.modelName)

            )
         

        }
    }
}
