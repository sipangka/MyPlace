package com.assignment.myplace.persistence

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.assignment.myplace.persistence.PlaceDao

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val dataSource: PlaceDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            return PlaceViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
