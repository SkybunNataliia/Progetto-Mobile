package com.corsolp.data.repository

import android.content.Context
import android.util.Log
import com.corsolp.data.local.PreferencesManager
import com.corsolp.data.remote.WeatherApi
import com.corsolp.data.remote.models.ForecastRemoteModel
import com.corsolp.data.remote.models.GeoLocationRemoteModel
import com.corsolp.data.remote.models.WeatherRemoteModel
import com.corsolp.domain.models.City
import com.corsolp.domain.models.Forecast
import com.corsolp.domain.models.ForecastItem
import com.corsolp.domain.models.Weather
import com.corsolp.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WeatherRepositoryImpl (
    private val weatherApi: WeatherApi,
    private val preferencesManager: PreferencesManager
) : WeatherRepository {

    companion object {
        private const val TAG = "WeatherRepoImpl"
    }

    override fun fetchFavorites(): Flow<Set<City>> {
        return preferencesManager.favoriteCities
    }

    override suspend fun addFavoriteCity(cityName: String): Boolean {

        val city = this.geocodeCity(cityName)
            ?: throw IllegalArgumentException("City not found: $cityName")

        val result = preferencesManager.addCity(city)
        Log.d(TAG, "Add to favorites: $cityName")

        return result
    }

    override suspend fun removeFavoriteCity(cityName: String): Boolean {

        val city = this.geocodeCity(cityName)
            ?: throw IllegalArgumentException("City not found: $cityName")

        val result = preferencesManager.removeCity(city)
        Log.d(TAG, "Remove from favorites: $cityName")

        return result
    }

    override suspend fun fetchCurrentWeather(cityName: String): Weather {

        val city = this.geocodeCity(cityName)
            ?: throw IllegalArgumentException("City not found: $cityName")

        val weather = weatherApi.getCurrentWeather(lat = city.lat, lon = city.lon)
        Log.d(TAG, "Received weather response: $weather")

        val domainWeather = weather.toDomain()
        Log.d(TAG, "Mapped domain weather: $domainWeather")

        return domainWeather
    }

    override suspend fun fetchForecast(cityName: String): Forecast {

        val city = this.geocodeCity(cityName)
            ?: throw IllegalArgumentException("City not found: $cityName")

        val forecast = weatherApi.getForecast(lat = city.lat, lon = city.lon)
        Log.d(TAG, "Received forecast response: $forecast")

        val domainForecast = forecast.toDomain()
        Log.d(TAG, "Mapped domain forecast: $domainForecast")

        return domainForecast
    }

    override suspend fun geocodeCity(cityName: String): City? {

        val response = weatherApi.geocodeCity(city = cityName)
        Log.d(TAG, "Geocoded city: $cityName")

        return response.firstOrNull()?.toDomain()
    }

    private fun GeoLocationRemoteModel.toDomain(): City = City(
        name = this.name,
        country = this.country,
        lat = this.lat,
        lon = this.lon,
    )

    private fun WeatherRemoteModel.toDomain(): Weather = Weather(
        name = this.weather.firstOrNull()?.name ?: "",
        temperature = this.main.temp.toDouble(),
        description = this.weather.firstOrNull()?.description ?: ""
    )

    private fun ForecastRemoteModel.toDomain(): Forecast = Forecast(
        items = this.list.map {
            ForecastItem(
                dateTime = it.date ?: "",
                weather = it.weather.firstOrNull()?.name ?: "",
                temperature = it.main.temp.toDouble(),
                tempMin = it.main.tempMin.toDouble(),
                tempMax = it.main.tempMax.toDouble(),
                description = it.weather.firstOrNull()?.description ?: ""
            )
        }
    )
}