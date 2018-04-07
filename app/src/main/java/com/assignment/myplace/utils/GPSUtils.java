package com.assignment.myplace.utils;

import android.content.Context;
import android.location.LocationManager;

public class GPSUtils {

    public static boolean isGPSEnabled(final Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
        return manager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }
}
