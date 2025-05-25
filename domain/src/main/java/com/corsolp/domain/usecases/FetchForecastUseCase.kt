package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.models.Forecast
import com.corsolp.domain.repository.WeatherRepository

interface FetchForecastUseCase {
    suspend fun invoke(city: City): Forecast
}

class FetchForecastUseCaseImpl (private val repository: WeatherRepository): FetchForecastUseCase {

    override suspend fun invoke(city: City): Forecast {
        require(city.name.isNotBlank()) { "Il nome della città non può essere vuoto" }

        val forecast = repository.fetchForecast(city = city)

        return forecast
    }
}