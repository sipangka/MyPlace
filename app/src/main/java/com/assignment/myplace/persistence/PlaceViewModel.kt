package com.assignment.myplace.persistence

import android.arch.lifecycle.ViewModel
import com.assignment.myplace.persistence.Place
import com.assignment.myplace.persistence.PlaceDao
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * View Model for the [UserActivity]
 */
class PlaceViewModel(private val dataSource: PlaceDao) : ViewModel() {

    /**
     * Get all places.
     * *
     * @return a [Flowable] when query success
     */
    fun getPlaceAll(): Flowable<List<Place>> {
        return dataSource.getPlaceAll()
    }

    /**
     * Insert the place.
     * @param place the new place
     * *
     * @return a [Completable] that completes when the place name is inserted
     */
    fun insertPlace(place: Place): Completable {
        return Completable.fromAction {
            dataSource.insertPlace(place)
        }
    }


    /**
     * Delete the place by id.
     * @param id the id of place to be deleted
     * *
     * @return a [Completable] that completes when the place is deleted
     */
    fun deletePlace(id: String): Completable {
        return Completable.fromAction {
            dataSource.deletePlaceById(id)
        }
    }

}
