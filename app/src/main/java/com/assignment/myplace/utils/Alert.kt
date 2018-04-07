package com.assignment.myplace.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object Alert {

    fun showError(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok, null)
        builder.setCancelable(false)
        builder.show()
    }

    fun showError(context: Context, resId: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(resId)
        builder.setPositiveButton(android.R.string.ok, null)
        builder.setCancelable(false)
        builder.show()
    }

    fun showError(context: Context, message: String, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setCancelable(false)
        builder.show()
    }

    fun showError(context: Context, resId: Int, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(resId)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setCancelable(false)
        builder.show()
    }

    fun showMessage(context: Context, title: String, message: String, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setCancelable(false)
        builder.show()
    }

    fun showMessage(context: Context, titleRes: Int, messageRes: Int, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleRes)
        builder.setMessage(messageRes)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setCancelable(false)
        builder.show()
    }

    fun showMessageOkCancel(context: Context, title: String, message: String, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.setCancelable(false)
        builder.show()
    }

    fun showMessageOkCancel(context: Context, titleResId: Int, messageResId: Int, onClickListener: DialogInterface.OnClickListener, cancelClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleResId)
        builder.setMessage(messageResId)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setNegativeButton(android.R.string.cancel, cancelClickListener)
        builder.setCancelable(false)
        builder.show()
    }

    fun showMessageOkCancel(context: Context, titleResId: Int, messageResId: Int, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleResId)
        builder.setMessage(messageResId)
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.setCancelable(false)
        builder.show()
    }


}
