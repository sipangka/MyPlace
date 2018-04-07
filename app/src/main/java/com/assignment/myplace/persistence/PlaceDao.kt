package com.assignment.myplace.persistence


import android.arch.persistence.room.*
import io.reactivex.Flowable


/**
 * Data Access Object for the places table.
 */
@Dao
interface PlaceDao {


    /**
     * Get a place all.

     * @return all places from the table.
     */
    @Query("SELECT * FROM Places")
    fun getPlaceAll(): Flowable<List<Place>>


    /**
     * Get a place by id.

     * @return the place from the table with a specific id.
     */
    @Query("SELECT * FROM Places WHERE place_id = :id")
    fun getPlaceById(id: String): Flowable<Place>


    /**
     * Insert a place in the database. If the place already exists, replace it.

     * @param place the place to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlace(place: Place)


    /**
     * Delete a place in the database with a specific id.

     * @param id the place id to be deleted.
     */
    @Query("DELETE FROM Places WHERE place_id = :id")
    fun deletePlaceById(id: String)


    /**
     * Delete all places.
     */
    @Query("DELETE FROM Places")
    fun deleteAllPlaces()


}