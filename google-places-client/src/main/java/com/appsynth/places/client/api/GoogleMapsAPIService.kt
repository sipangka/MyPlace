package com.appsynth.places.client.api

import com.appsynth.places.client.model.GeoCodeReverseResponse
import com.appsynth.places.client.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Interface for Google API endpoints
 */
interface GoogleMapsAPIService{

    /**
     * Nearby Search - Responsible for preparing and executing the nearby search
     * @param key - String Google API Key
     * @param location - Coordinates Object for the origin of the search
     * @param radius - Integer representing the radius of the search (in meters)
     *
     */
    @POST("place/nearbysearch/json")
    fun getNearbyPlaces(@QueryMap params:Map<String,String> ): Observable<SearchResponse>


    /**
     * Reverse geocoding - Responsible for preparing and executing the reverse Geocoding
     * @param key - String Google API Key
     * @param latlng - Coordinates Object for the location of the interested address
     *
     */
    @POST("geocode/json")
    fun getAddress(@QueryMap params:Map<String,String> ): Observable<GeoCodeReverseResponse>

//https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
}