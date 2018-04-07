package com.assignment.myplace.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by mys_p on 15/3/2561.
 */

public class Network {

    public static boolean isNetworkAvailable(final Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
