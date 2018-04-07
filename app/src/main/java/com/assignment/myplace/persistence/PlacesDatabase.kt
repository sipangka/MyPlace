
package com.assignment.myplace.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * The Room database that contains the Users table
 */
@Database(entities = arrayOf(Place::class), version = 1)
abstract class PlacesDatabase : RoomDatabase() {

    abstract fun placeDao(): PlaceDao

    companion object {

        @Volatile private var INSTANCE: PlacesDatabase? = null

        fun getInstance(context: Context): PlacesDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        PlacesDatabase::class.java, "Place.db")
                        .build()
    }
}
