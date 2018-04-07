package com.assignment.myplace.persistence

import android.arch.persistence.room.*
import com.appsynth.places.client.model.Coordinates

/**
 * Data Model to describe the location returned from Google Places API
 *
 * @param location - Coordinates Object to hold latitude and longitude
 * @param iconLink - URL of the place icon
 * @param placeId - String ID of the Place
 * @param name - Name of the Place
 * @param rating - Rating of place (Stars Count)
 * @param vicinity - contains a feature name of a nearby location.
 * @param address - address.
 * @param favorite - favorite.
 *
 */
@Entity(tableName = "places")
@TypeConverters(PlaceTypeConverters::class)
data class Place(@PrimaryKey @ColumnInfo(name = "place_id") var placeId:String,
                 @ColumnInfo(name = "icon_link") var iconLink:String,
                 @Embedded var location: Coordinates,
                 var id:String,
                 var name:String,
                 var rating:Int,
                 var vicinity:String,
                 var type:List<String>,
                 var address:String? = "",
                 var favorite:Boolean? = false)
