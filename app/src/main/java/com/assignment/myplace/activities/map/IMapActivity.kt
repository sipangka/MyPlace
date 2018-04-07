package com.assignment.myplace.activities.map


import android.content.Context
import com.assignment.myplace.base.BasePresenter
import com.assignment.myplace.base.BaseView
import com.google.android.gms.maps.model.LatLng


interface IMapActivity {

    interface View : BaseView<Presenter> {
        fun getContext() : Context
        fun initMap(location: LatLng)
        fun showAddress(address : String)
        fun onAddressClick(location : LatLng)
        fun getExtras()
        fun init()
    }

    interface Presenter : BasePresenter {
        fun onMapReady()
        fun onMapCameraMove(point : LatLng?)
        fun getCurrentLocation()
        fun getAddress()
    }

}
