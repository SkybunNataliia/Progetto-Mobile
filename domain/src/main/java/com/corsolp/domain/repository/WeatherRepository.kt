package com.corsolp.domain.repository

import com.corsolp.domain.models.City
import com.corsolp.domain.models.Forecast
import com.corsolp.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun fetchFavorites(): Flow<Set<City>>
    suspend fun addFavoriteCity(city: City): Boolean
    suspend fun removeFavoriteCity(city: City): Boolean

    suspend fun fetchCurrentWeather(city: City): Weather

    suspend fun fetchForecast(city: City): Forecast
}