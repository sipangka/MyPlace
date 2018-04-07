package com.assignment.myplace.fragments.favorite


import android.content.Context
import com.assignment.myplace.persistence.Place
import com.google.android.gms.maps.model.LatLng


interface IFavoriteFragment {
    interface View {
        fun setData(places: List<Place>)
        fun gotoMap(location: LatLng?)
        fun getContext(): Context
    }

    interface Presenter {
        fun onViewCreated()
        fun onViewDestroy()
        fun getFavoritPlaces()
        fun removeFavoritePlace(place : Place)
        //fun addGeoFence(place: Place)
        //fun deleteGeoFence(place: Place)
    }
}
