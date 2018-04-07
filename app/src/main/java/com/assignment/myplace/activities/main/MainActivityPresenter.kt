package com.assignment.myplace.activities.main

import com.assignment.myplace.datamanagers.FavoritePlacesManager
import com.assignment.myplace.persistence.Place
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class MainActivityPresenter(private val view: IMainActivity.View) : IMainActivity.Presenter,
FavoritePlacesManager.Listener{

    val TAG = MainActivityPresenter::class.java.simpleName

    val mCompositeDisposable = CompositeDisposable()

    override fun viewOnCreate() {

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

    companion object {
        private val TAG = MainActivityPresenter::class.java.simpleName
    }

    /*override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (PermissionUtils.onRequestPermissionsResultPass(requestCode, permissions, grantResults, view.getContext() as Activity)) {
            view.initInstance()
        } else {
            view.showAlertPermissionDenied()
        }
    }*/


    /**
     *
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

}
