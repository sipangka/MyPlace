package com.assignment.myplace.datamanagers


import android.content.Context
import com.appsynth.places.client.RetrofitHelper
import com.appsynth.places.client.model.Coordinates
import com.appsynth.places.client.model.GeoCodeReverseResponse
import com.assignment.myplace.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GeocodeManager(val context : Context) {

    interface Listener{
        fun onGeocodeResultsReady(result:GeoCodeReverseResponse)
        fun onGeocodeError(throwable: Throwable)
        fun onGeocodeComplete()
    }

    val apiKey: String

    init {
        apiKey = context.getString(R.string.google_maps_key_restrictions_none)
    }

    fun generateGeocodeParamsHashMap(coordinates: Coordinates): java.util.HashMap<String, String> {
        val map: java.util.HashMap<String, String> = java.util.HashMap()
        map.put("key",apiKey)
        map.put("latlng","${coordinates.latitude},${coordinates.longitude}")
        return  map
    }


    fun getAddress(coordinates: Coordinates, listener: GeocodeManager.Listener, mCompositeDisposable : CompositeDisposable) {

        val parametersMap: HashMap<String, String>
        parametersMap = generateGeocodeParamsHashMap(coordinates)!!
        val googleMapsAPIService = RetrofitHelper().googleMapsAPIService
        var observable = googleMapsAPIService.getAddress(parametersMap)


        mCompositeDisposable?.add(observable
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> result?.let { listener.onGeocodeResultsReady(it) } },
                        { error -> listener.onGeocodeError(error) },
                        { listener.onGeocodeComplete() }
                ))
    }

}