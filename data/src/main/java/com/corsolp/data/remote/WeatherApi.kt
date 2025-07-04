package com.corsolp.data.remote

import com.corsolp.data.remote.models.ForecastRemoteModel
import com.corsolp.data.remote.models.GeoLocationRemoteModel
import com.corsolp.data.remote.models.WeatherRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "it"
    ): WeatherRemoteModel

    @GET("/data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "it"
    ): ForecastRemoteModel

    @GET("/geo/1.0/direct")
    suspend fun geocodeCity(
        @Query("q") city: String,
        @Query("limit") limit: Int = 1
    ): List<GeoLocationRemoteModel>
}
