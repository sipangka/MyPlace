
package com.assignment.myplace.persistence

import android.content.Context
import com.assignment.myplace.persistence.ViewModelFactory

/**
 * Enables injection of data sources.
 */
object Injection {

    fun provideUserDataSource(context: Context): PlaceDao {
        val database = PlacesDatabase.getInstance(context)
        return database.placeDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}
