package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.models.Weather
import com.corsolp.domain.repository.WeatherRepository


interface FetchCurrentWeatherUseCase {
    suspend fun invoke(cityName: String): Weather
}

class FetchCurrentWeatherUseCaseImpl (private val repository: WeatherRepository): FetchCurrentWeatherUseCase {

    override suspend fun invoke(cityName: String): Weather {
        require(cityName.isNotBlank()) { "Il nome della città non può essere vuoto" }

        val weather = repository.fetchCurrentWeather(cityName = cityName)

        return weather
    }
}