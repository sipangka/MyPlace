package com.assignment.myplace.datamanagers


import android.content.Context
import com.appsynth.places.client.RetrofitHelper
import com.appsynth.places.client.model.Coordinates
import com.appsynth.places.client.model.SearchResponse
import com.assignment.myplace.R
import com.assignment.myplace.persistence.Place
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class NearbyPlacesManager(val context: Context, var radius : Int = 1000) {

    interface Listener{
        fun onNearbySearchResultsReady(places:List<Place>)
        fun onNearbySearchError(throwable: Throwable)
        fun onNearbySearchComplete()
    }

    val apiKey: String

    init {
        apiKey = context.getString(R.string.google_map_api_key)
    }

    fun generateNearbyPlacesParamsHashMap(coordinates: Coordinates, radius: Int): java.util.HashMap<String, String> {
        val map: java.util.HashMap<String, String> = java.util.HashMap()
        map.put("key", apiKey)
        map.put("location","${coordinates.latitude},${coordinates.longitude}")
        map.put("radius", radius.toString())
        return  map
    }

    /**
     * Parses JSON response from the server and returns list of places (Search Results)
     * @param result - String representation of JSON results
     * @return List<Place> - list of places returned from the server
     */
    fun createPlaceList(results : SearchResponse) : List<Place>?{

        val placesResponse = results.results
        val places: ArrayList<Place> = ArrayList()

        placesResponse.forEach {
            places.add(
                    Place(
                            name = it.name,
                            location = Coordinates(
                                    latitude = it.geometry.location.lat,
                                    longitude = it.geometry.location.lng
                            ),
                            iconLink = it.icon,
                            id = it.id ,
                            placeId = it.placeId ,
                            rating = 0,
                            vicinity = it.vicinity ,
                            type = it.types,
                            address = if(it.formattedAddress != null) it.formattedAddress else ""
                    ))

        }
        return places
    }


    fun createNearbyPlacesObservable(coordinates: Coordinates): Observable<SearchResponse> {
        val parametersMap: HashMap<String, String>
        parametersMap = generateNearbyPlacesParamsHashMap(coordinates, radius)
        val googleMapsAPIService = RetrofitHelper().googleMapsAPIService
        var observable = googleMapsAPIService.getNearbyPlaces(parametersMap)

        return  observable
    }


    fun getNearbyPlaces(coordinates: Coordinates, listener: NearbyPlacesManager.Listener, mCompositeDisposable : CompositeDisposable) {

        var observable = createNearbyPlacesObservable(coordinates)

        mCompositeDisposable?.add(observable
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .map  { searchResponse ->
                    createPlaceList(searchResponse)
                }
                .subscribe(
                        { result -> result?.let { listener.onNearbySearchResultsReady(it) } },
                        { error -> listener.onNearbySearchError(error) },
                        { listener.onNearbySearchComplete() }
                ))
    }


}