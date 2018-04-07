package com.assignment.myplace.activities.map

import android.app.Activity
import android.content.DialogInterface
import android.os.Build
import android.os.Handler
import com.appsynth.places.client.model.Coordinates
import com.appsynth.places.client.model.GeoCodeReverseResponse
import com.assignment.myplace.R
import com.assignment.myplace.datamanagers.GeocodeManager
import com.assignment.myplace.utils.Alert
import com.assignment.myplace.utils.PermissionUtils
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable


class MapActivityPresenter(private val view: IMapActivity.View) : IMapActivity.Presenter
        , GeocodeManager.Listener{

    val TAG = MapActivityPresenter::class.java.simpleName

    var point: LatLng? = null
    val mCompositeDisposable = CompositeDisposable()
    var geocodeManager : GeocodeManager? = null


    override fun viewOnCreate() {
        geocodeManager = GeocodeManager(view.getContext())
        view.getExtras()
        view.init()
    }

    override fun viewOnStart() {

    }

    override fun viewOnResume() {

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

    override fun onMapReady(point: LatLng?) {
        this.point = point
        if(point == null){
            this.point = LatLng(13.7698016,100.5735115)//appsynth
        }

        view.initMap(this.point!!)
        getAddress()
    }

    override fun onMapCameraMove(point : LatLng?) {
        this.point = point
        //getAddress(point)
    }

    override fun getCurrentLocation() {
        point?.let { view.onAddressClick(it) }
    }

    override fun getAddress() {
        point?.let {
            mCompositeDisposable.clear()
            geocodeManager?.getAddress(Coordinates(it.latitude, it.longitude), this, mCompositeDisposable )
        }
    }

    override fun onGeocodeResultsReady(result: GeoCodeReverseResponse) {

        result.addresses?.let{
            if(it.size > 0){
                view.showAddress(it.get(0).formattedAddress)
            }
        }

    }

    override fun onGeocodeError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun onGeocodeComplete() {
        println("Complete!!")
    }
}
