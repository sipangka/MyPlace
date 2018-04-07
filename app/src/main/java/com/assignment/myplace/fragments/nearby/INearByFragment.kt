package com.assignment.myplace.fragments.nearby


import android.content.Context
import android.content.Intent
import com.assignment.myplace.persistence.Place
import com.google.android.gms.maps.model.LatLng


interface INearByFragment {
    interface View {
        fun setData(places: List<Place>)
        fun gotoMap(location: LatLng?)
        fun buildAlertMessageNoGps()
        fun getContext() : Context

    }

    interface Presenter {
        fun onViewCreated()
        fun onViewDestroy()
        fun onViewResume()
        fun getCurrentLocation()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun getNearbyPlaces(coordinates: LatLng)
        fun getFavoritePlaces()
        fun getPlaces(coordinates: LatLng)
        fun addFavoritPlace(place : Place)
        fun removeFavoritPlace(place : Place)

    }
}
