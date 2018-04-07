package com.assignment.myplace.fragments.favorite


import com.assignment.myplace.datamanagers.FavoritePlacesManager
import com.assignment.myplace.persistence.Place
import com.assignment.myplace.service.GeofenceController
import io.reactivex.disposables.CompositeDisposable


class FavoriteFragmentPresenter(private val view: IFavoriteFragment.View) : IFavoriteFragment.Presenter
    ,FavoritePlacesManager.Listener{


    val mCompositeDisposable = CompositeDisposable()
    var favoritePlacesManager : FavoritePlacesManager? = null
    var favoritePlaceList : List<Place>? = null
    var geoFenceListUpdateListener : GeoFenceListUpdateListener? = null

    interface GeoFenceListUpdateListener {
        fun onFavoritePlaceUpdate(places: List<Place>);
    }

    override fun onViewCreated() {
        favoritePlacesManager = FavoritePlacesManager(view.getContext())
        getFavoritPlaces()

        geoFenceListUpdateListener = view.getContext() as GeoFenceListUpdateListener
    }

    override fun onViewDestroy() {
        mCompositeDisposable.clear()
    }


    override fun getFavoritPlaces() {
       favoritePlacesManager?.getFavoritePlaces(this, mCompositeDisposable)
    }


    override fun removeFavoritePlace(place: Place) {
        favoritePlacesManager?.removeFavoritePlace(place,this, mCompositeDisposable)
    }

    /**
     * Implement FavoritePlacesManager.Listener
     */
    override fun onFavoritePlacesResultsReady(places: List<Place>) {// Implement NearbySearchListener
        view.setData(places)
        GeofenceController.getInstance().placeGeofences = places
        geoFenceListUpdateListener?.onFavoritePlaceUpdate(places)
    }

    override fun onFavoritePlacesError(throwable: Throwable) {// Implement NearbySearchListener
        throwable.printStackTrace()
    }

    override fun onFavoritePlacesComplete() {

    }

    override fun onFavoritePlacesInsertComplete() {
        getFavoritPlaces()
    }

    override fun onFavoritePlacesDeleteComplete() {
        getFavoritPlaces()
    }


}
