package com.corsolp.data.remote

import com.corsolp.data.remote.models.ForecastRemoteModel
import com.corsolp.data.remote.models.GeoLocationRemoteModel
import com.corsolp.data.remote.models.WeatherRemoteModel

class WeatherRemoteDataSource(private val api: WeatherApi) {

    private suspend fun geocodeCity(city: String): GeoLocationRemoteModel? {
        return api.geocodeCity(city).firstOrNull()
    }

    suspend fun getCurrentWeather(city: String): WeatherRemoteModel? {
        val geo = geocodeCity(city)
        return geo?.let {
            api.getCurrentWeather(lat = it.lat, lon = it.lon)
        }
    }

    suspend fun getForecast(city: String): ForecastRemoteModel? {
        val geo = geocodeCity(city)
        return geo?.let {
            api.getForecast(lat = it.lat, lon = it.lon)
        }
    }
}
