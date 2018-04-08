package com.assignment.myplace.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

import com.assignment.myplace.R


object PermissionUtils {

    val REQUEST_CODE_ASK_PERMISSIONS = 101

    /**
     *
     * @param activity
     * @return true if app already has permission
     */
    fun requestPermissions(activity: Activity): Boolean {
        val hasLocationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)


        if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(activity).setTitle(R.string.dialog_information)
                        .setMessage(R.string.dialog_request_permission)
                        .setPositiveButton(android.R.string.ok) { dialog, which -> ActivityCompat.requestPermissions(activity, arrayOf( Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_ASK_PERMISSIONS) }.setCancelable(false).show()
                return false
            }
            ActivityCompat.requestPermissions(activity, arrayOf( Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_ASK_PERMISSIONS)
            return false
        }
        return true
    }

    fun onRequestPermissionsResultPass(requestCode: Int, permissions: Array<String>, grantResults: IntArray, activity: Activity): Boolean {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> return grantResults[0] == PackageManager.PERMISSION_GRANTED
            else -> return false
        }
    }

    /**
     *
     * @param activity
     * @return true if app already has permission
     */
    fun requestPermissions(activity: Activity, vararg permissions: String): Boolean {
        val hasLocationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)


        if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(activity).setTitle(R.string.dialog_information)
                        .setMessage(R.string.dialog_request_permission)
                        .setPositiveButton(android.R.string.ok) { dialog, which -> ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_ASK_PERMISSIONS) }.setCancelable(false).show()
                return false
            }
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_ASK_PERMISSIONS)
            return false
        }
        return true
    }


}
