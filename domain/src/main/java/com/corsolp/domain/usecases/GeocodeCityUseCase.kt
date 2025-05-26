package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.repository.WeatherRepository

interface GeocodeCityUseCase {
    suspend fun invoke(cityName: String): City?
}

class GeocodeCityUseCaseImpl(private val repository: WeatherRepository) : GeocodeCityUseCase {

    override suspend fun invoke(cityName: String): City? {
        require(cityName.isNotBlank()) { "Il nome della città non può essere vuoto" }

        return repository.geocodeCity(cityName)
    }
}