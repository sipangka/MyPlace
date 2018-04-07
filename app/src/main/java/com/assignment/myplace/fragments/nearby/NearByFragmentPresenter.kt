package com.assignment.myplace.fragments.nearby


import android.content.Intent
import android.util.Log
import com.appsynth.places.client.model.Coordinates
import com.assignment.myplace.activities.map.MapActivity
import com.assignment.myplace.datamanagers.FavoritePlacesManager
import com.assignment.myplace.datamanagers.NearbyPlacesManager
import com.assignment.myplace.persistence.Place
import com.assignment.myplace.utils.ArrayUtils
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import java.util.*


class NearByFragmentPresenter(private val view: INearByFragment.View) : INearByFragment.Presenter
    , NearbyPlacesManager.Listener
    , FavoritePlacesManager.Listener{

    companion object {
        val TAG = NearByFragmentPresenter::class.java.simpleName
    }

    val mCompositeDisposable = CompositeDisposable()
    var favoritePlacesManager : FavoritePlacesManager? = null
    var nearbyPlacesManager : NearbyPlacesManager? = null

    var nearbyPlaceList : List<Place>? = null
    var favoritePlaceList : List<Place>? = null

    override fun onViewCreated() {
        favoritePlacesManager = FavoritePlacesManager(view.getContext())
        nearbyPlacesManager = NearbyPlacesManager(view.getContext())
        getNearbyPlaces(LatLng(13.7698016,100.5735115))
    }


    override fun onViewDestroy() {
        mCompositeDisposable.clear()
    }

    override fun onViewResume() {
        getFavoritePlaces()
    }

    override fun getCurrentLocation() {

       /* if(!GPSUtils.isGPSEnabled(view.getContext())){
            view.buildAlertMessageNoGps()
        }else{
            // getCurrent Location
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(data?.hasExtra(MapActivity.LOCATION_KEY)!!){
            var location = data.getParcelableExtra<LatLng>(MapActivity.LOCATION_KEY)
            getNearbyPlaces(location)
        }

    }


    override fun getNearbyPlaces(coordinates: LatLng) {
        nearbyPlacesManager?.getNearbyPlaces(Coordinates(coordinates.latitude, coordinates.longitude), this, mCompositeDisposable )
    }

    override fun getFavoritePlaces() {
        favoritePlacesManager?.getFavoritePlaces(this, mCompositeDisposable)
    }

    override fun getPlaces(coordinates: LatLng) {


    }

    override fun addFavoritPlace(place: Place) {
        favoritePlacesManager?.addFavoritePlace(place, this, mCompositeDisposable)
    }

    override fun removeFavoritPlace(place: Place) {
        favoritePlacesManager?.removeFavoritePlace(place, this, mCompositeDisposable)
    }

    /**
     * Implement NearbyPlacesManager.Listener
     */
    override fun onNearbySearchResultsReady(places: List<Place>) {// Implement NearbySearchListener
        nearbyPlaceList = places
        view.setData(places)

        favoritePlacesManager?.getFavoritePlaces(this, mCompositeDisposable)
    }

    override fun onNearbySearchError(throwable: Throwable) {// Implement NearbySearchListener
        throwable.printStackTrace()
    }

    override fun onNearbySearchComplete() {

    }


    /**
     * Implement FavoritePlacesManager.Listener
     */

    override fun onFavoritePlacesResultsReady(places: List<Place>) {
        favoritePlaceList = places

        if(nearbyPlaceList != null && nearbyPlaceList?.size!! > 0
                && favoritePlaceList != null && favoritePlaceList!!.size > 0){
            var newPlaceList = ArrayUtils.merge(list1 = nearbyPlaceList!!, list2 = favoritePlaceList!!)

            newPlaceList.let {
                view.setData(it)
            }
        }else if(nearbyPlaceList != null && nearbyPlaceList?.size!! > 0){
            nearbyPlaceList?.forEach {
                it.favorite = false
            }
            view.setData(nearbyPlaceList!!)
        }
    }

    override fun onFavoritePlacesError(throwable: Throwable) {
        throwable.printStackTrace()
        Log.e(TAG, throwable.message)
    }

    override fun onFavoritePlacesComplete() {
        Log.e(TAG, "Complete")
    }

    override fun onFavoritePlacesInsertComplete() {
        Log.e(TAG, "Insert Complete")
        favoritePlacesManager?.getFavoritePlaces(this, mCompositeDisposable)
    }

    override fun onFavoritePlacesDeleteComplete() {
        Log.e(TAG, "Delete Complete")
        favoritePlacesManager?.getFavoritePlaces(this, mCompositeDisposable)
    }
}
