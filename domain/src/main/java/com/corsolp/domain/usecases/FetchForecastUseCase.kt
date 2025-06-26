package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.models.Forecast
import com.corsolp.domain.repository.WeatherRepository

interface FetchForecastUseCase {
    suspend operator fun invoke(cityName: String): Forecast
}

class FetchForecastUseCaseImpl (private val repository: WeatherRepository): FetchForecastUseCase {

    override suspend fun invoke(cityName: String): Forecast {
        require(cityName.isNotBlank()) { "Il nome della città non può essere vuoto" }

        val forecast = repository.fetchForecast(cityName = cityName)

        return forecast
    }
}