package com.corsolp.domain.repository

import com.corsolp.domain.models.City
import com.corsolp.domain.models.Forecast
import com.corsolp.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun fetchFavorites(): Flow<Set<City>>
    suspend fun addFavoriteCity(cityName: String): Boolean
    suspend fun removeFavoriteCity(cityName: String): Boolean

    suspend fun fetchCurrentWeather(cityName: String): Weather

    suspend fun fetchForecast(cityName: String): Forecast

    suspend fun geocodeCity(cityName: String): City?
}