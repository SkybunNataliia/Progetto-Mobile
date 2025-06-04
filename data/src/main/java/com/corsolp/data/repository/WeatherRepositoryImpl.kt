package com.corsolp.data.repository

import android.content.Context
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
    private val context: Context,
    private val weatherApi: WeatherApi
) : WeatherRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun fetchFavorites(): Flow<Set<City>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteCity(cityName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavoriteCity(cityName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCurrentWeather(cityName: String): Weather {
        val city = this.geocodeCity(cityName)
            ?: throw IllegalArgumentException("City not found: $cityName")
        val weather = weatherApi.getCurrentWeather(lat = city.lat, lon = city.lon)
        return weather.toDomain()
    }

    override suspend fun fetchForecast(cityName: String): Forecast {
        TODO("Not yet implemented")
    }

    override suspend fun geocodeCity(cityName: String): City? {
        val response = weatherApi.geocodeCity(city = cityName)
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