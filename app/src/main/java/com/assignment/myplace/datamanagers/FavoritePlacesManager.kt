package com.assignment.myplace.datamanagers


import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.appsynth.places.client.model.Coordinates
import com.appsynth.places.client.model.SearchResponse
import com.assignment.myplace.activities.map.MapActivity
import com.assignment.myplace.persistence.Injection
import com.assignment.myplace.persistence.Place
import com.assignment.myplace.persistence.PlaceViewModel
import com.assignment.myplace.persistence.ViewModelFactory
import io.reactivex.Flowable
//import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*

class FavoritePlacesManager(val context: Context) {

    interface Listener{
        fun onFavoritePlacesResultsReady(places:List<Place>)
        fun onFavoritePlacesError(throwable: Throwable)
        fun onFavoritePlacesComplete()
        fun onFavoritePlacesInsertComplete()
        fun onFavoritePlacesDeleteComplete()
    }

    companion object {
        val TAG = FavoritePlacesManager::class.java.simpleName
    }

    private var viewModelFactory: ViewModelFactory
    private var viewModel: PlaceViewModel

    init {
        viewModelFactory = Injection.provideViewModelFactory(context)
        viewModel = ViewModelProviders.of(context as AppCompatActivity, viewModelFactory).get(PlaceViewModel::class.java)
    }

    fun createFavoritePlaceObservable() : Flowable<List<Place>> {

        return viewModel.getPlaceAll()
    }

    fun getFavoritePlaces(listener: FavoritePlacesManager.Listener, disposable : CompositeDisposable) {

        disposable.add(viewModel.getPlaceAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { results -> listener.onFavoritePlacesResultsReady(results) },
                        { error -> listener.onFavoritePlacesError(error) },
                        { listener.onFavoritePlacesComplete()}))
    }

    fun addFavoritePlace(place : Place, listener: FavoritePlacesManager.Listener, disposable : CompositeDisposable) {

        disposable.add(viewModel.insertPlace(place)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onFavoritePlacesInsertComplete() },
                        { error -> listener.onFavoritePlacesError(error)}))
    }

    fun removeFavoritePlace(place : Place, listener: FavoritePlacesManager.Listener, disposable : CompositeDisposable) {

        disposable.add(viewModel.deletePlace(place.placeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listener.onFavoritePlacesDeleteComplete() },
                        { error -> listener.onFavoritePlacesError(error)}))
    }


}