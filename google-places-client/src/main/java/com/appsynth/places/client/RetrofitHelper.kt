package com.appsynth.places.client

import com.appsynth.places.client.api.GoogleMapsAPIService
import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class initializes retrofit with a default configuration.
 * You can use this class to initialize the different services.
 */

class RetrofitHelper {

    /**
     * The CityService communicates with the json api of the city provider.
     */
    val googleMapsAPIService: GoogleMapsAPIService
        get() {
            val retrofit = createRetrofit()
            return retrofit.create(GoogleMapsAPIService::class.java)
        }

    /**
     * This custom client will append the "username=demo" query after every request.
     */
    private fun createOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        /*httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("username", "demo")
                    .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }*/

        httpClient.addInterceptor(LoggingInterceptor())

        return httpClient.build()
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- add this
                .client(createOkHttpClient())
                .build()
    }

}
