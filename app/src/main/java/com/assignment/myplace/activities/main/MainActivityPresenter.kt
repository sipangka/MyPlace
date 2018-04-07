package com.assignment.myplace.activities.main

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.assignment.myplace.R
import com.assignment.myplace.datamanagers.FavoritePlacesManager
import com.assignment.myplace.persistence.Place
import com.assignment.myplace.service.GeofenceController
import com.assignment.myplace.service.GeofenceIntentService
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class MainActivityPresenter(private val view: IMainActivity.View) : IMainActivity.Presenter,
FavoritePlacesManager.Listener,
GeofenceController.GeofenceControllerListener{

    val TAG = MainActivityPresenter::class.java.simpleName

    val mCompositeDisposable = CompositeDisposable()
    var geofenceController : GeofenceController? = null

    override fun viewOnCreate() {
        GeofenceController.getInstance().init(view.getContext(),null, this)
        geofenceController = GeofenceController.getInstance();
    }


    override fun viewOnStart() {

    }

    override fun viewOnResume() {
        val googlePlayServicesCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(view.getContext())
        Log.i(TAG, "googlePlayServicesCode = $googlePlayServicesCode")

        if (googlePlayServicesCode == 1 || googlePlayServicesCode == 2 || googlePlayServicesCode == 3) {
            val apiAvailability = GoogleApiAvailability.getInstance()
            apiAvailability.getErrorDialog(view.getContext() as Activity, googlePlayServicesCode, 0).show()
        }
    }

    override fun viewOnPause() {

    }

    override fun viewOnStop() {

    }

    override fun viewOnDestroy() {
        mCompositeDisposable.clear()
    }

    override fun viewOnCreateView() {

    }

    override fun viewOnDestroyView() {

    }

    override fun addGeoFenceList(places: List<Place>) {
        geofenceController?.placeGeofences = places
    }


    /**
     * Implement FavoritePlacesManager.Listener
     */
    override fun onFavoritePlacesResultsReady(places: List<Place>) {

    }

    override fun onFavoritePlacesError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun onFavoritePlacesComplete() {
        println("complete!!")
    }


    override fun onFavoritePlacesInsertComplete() {

    }

    override fun onFavoritePlacesDeleteComplete() {

    }

    /**
     * Implement GeofenceController.GeofenceControllerListener
     */
    override fun onGeofencesUpdated() {
        Toast.makeText(view.getContext(), view.getContext()!!.getString(R.string.Toast_Update), Toast.LENGTH_SHORT).show()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(view.getContext(), view.getContext()!!.getString(R.string.Toast_Error) + " " + throwable.message , Toast.LENGTH_SHORT).show()
    }

}
