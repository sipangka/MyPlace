package com.assignment.myplace.service;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.assignment.myplace.activities.main.MainActivity;
import com.assignment.myplace.persistence.Place;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GeofenceController {

    // region Properties

    private final String TAG = GeofenceController.class.getName();

    public interface GeofenceControllerListener {
        void onGeofencesUpdated();
        void onError(Throwable throwable);
    }

    private Context context;
    private GeofenceControllerListener listener;
    private List<Place> placeGeofences;

    public List<Place> getPlaceGeofences() {
        return placeGeofences;
    }

    private List<Geofence> mGeofenceList = new ArrayList<Geofence>();

    private GeofencingClient mGeofencingClient;
    private PendingIntent mGeofencePendingIntent;
    private boolean startMonitoring = false;


    private static GeofenceController INSTANCE;

    public static GeofenceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GeofenceController();
        }
        return INSTANCE;
    }


    public void init(Context context, List<Place> places, GeofenceControllerListener listener) {
        this.context = context.getApplicationContext();
        this.mGeofencingClient = LocationServices.getGeofencingClient(context);
        this.listener = listener;

        this.placeGeofences = places;

        if(placeGeofences != null && placeGeofences.size() > 0){
            addGeoFences();
        }
    }

    public void setPlaceGeofences(List<Place> places){
        placeGeofences = places;

        if(startMonitoring){
            stopGeoFences();
        }
        addGeoFences();
    }


    private GeofencingRequest getAddGeofencingRequest() {

        mGeofenceList.clear();
        for (Place p : placeGeofences){
            mGeofenceList.add(p.geofence());
        }

        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }


    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(context, GeofenceIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        mGeofencePendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }

    private void addGeoFences() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
            return;
        }

        mGeofencingClient.addGeofences(getAddGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "addGeofences success");
                        startMonitoring = true;

                        listener.onGeofencesUpdated();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "addGeofences fail", e);
                startMonitoring = false;
                listener.onError(e);
            }
        });

    }

    private void stopGeoFences(){
        mGeofencingClient.removeGeofences(getGeofencePendingIntent())
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Geofences removed
                        Log.d(TAG, "removeGeofences success");
                        startMonitoring = false;
                    }
                })
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to remove geofences
                        Log.e(TAG, "removeGeofences fail", e);
                    }
                });
    }

}