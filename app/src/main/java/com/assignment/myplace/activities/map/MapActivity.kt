package com.assignment.myplace.activities.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.assignment.myplace.R
import com.assignment.myplace.adapters.CustomInfoWindowAdapter
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_map.*
import android.view.View


class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener
        , IMapActivity.View{

    companion object {
        val TAG = MapActivity::class.java.simpleName
        val LOCATION_KEY = "location"
    }

    var mPresenter: IMapActivity.Presenter? = null

    var mMap: GoogleMap? = null
    var point : LatLng? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mPresenter = MapActivityPresenter(this)
        mPresenter?.viewOnCreate()

    }

    override fun getExtras() {
        if(intent.hasExtra(LOCATION_KEY)){
            point = intent.getParcelableExtra(LOCATION_KEY)
        }
    }

    override fun init(){
        var params = RelativeLayout.LayoutParams(markerLayout.layoutParams.width, markerLayout.layoutParams.height)
        rootLayout.post(Runnable {
            val height = rootLayout.getHeight()
            val weight = rootLayout.getWidth()

            val h = markerLayout.height
            val w = markerLayout.width

            params.leftMargin = ((weight/2).toInt() - w/2)
            params.topMargin = ((height/2).toInt() - h)

            rootLayout.removeView(markerLayout)
            rootLayout.addView(markerLayout, params)

        })

        addressLayout.setOnClickListener(View.OnClickListener { mPresenter?.getCurrentLocation()})
        buttonSelectLocation.setOnClickListener(View.OnClickListener { mPresenter?.getCurrentLocation()})
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mPresenter?.onMapReady(point)
    }


    //////////////////////////////////////////////////////////////////

    override fun onMapClick(p0: LatLng?) {

    }

    override fun initMap(location: LatLng) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;

        }else{
            mMap?.setMyLocationEnabled(true)
        }

        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        // Zoom in the Google Map
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(15f))


        mMap?.setOnCameraMoveListener {
            point = mMap?.cameraPosition?.target!!
            mPresenter?.onMapCameraMove(point!!)
        }

        mMap?.setOnCameraIdleListener {
            mPresenter?.getAddress();
        }
    }

    override fun showAddress(address: String) {
        addressTV.text = address
    }

    override fun onAddressClick(location: LatLng) {
        var data = Intent()
        data.putExtra(LOCATION_KEY, location)
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onDestroy() {
        mPresenter?.viewOnDestroy()
        super.onDestroy()
    }

    override fun getContext(): Context {
        return this
    }
}


