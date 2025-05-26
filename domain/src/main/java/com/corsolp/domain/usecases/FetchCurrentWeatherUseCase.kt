package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.models.Weather
import com.corsolp.domain.repository.WeatherRepository


interface FetchCurrentWeatherUseCase {
    suspend fun invoke(city: City): Weather
}

class FetchCurrentWeatherUseCaseImpl (private val repository: WeatherRepository): FetchCurrentWeatherUseCase {

    override suspend fun invoke(city: City): Weather {
        require(city.name.isNotBlank()) { "Il nome della città non può essere vuoto" }

        val weather = repository.fetchCurrentWeather(city = city)

        return weather
    }
}