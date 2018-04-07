package com.assignment.myplace.activities.main


import android.content.Context
import com.assignment.myplace.base.BasePresenter
import com.assignment.myplace.base.BaseView
import com.assignment.myplace.persistence.Place


interface IMainActivity {

    interface View : BaseView<Presenter> {
        fun getContext() : Context
    }

    interface Presenter : BasePresenter {
        //fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
        fun addGeoFenceList(places : List<Place>)
    }

}
