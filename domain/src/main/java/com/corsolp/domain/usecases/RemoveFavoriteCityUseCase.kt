package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.repository.WeatherRepository

interface RemoveFavoriteCityUseCase {
    suspend fun invoke(cityName: String): Boolean
}

class RemoveFavoriteCityUseCaseImpl(private val repository: WeatherRepository) : RemoveFavoriteCityUseCase {

    override suspend fun invoke(cityName: String): Boolean {

        require(cityName.isNotBlank()) { "Il nome della città non può essere vuoto" }

        return repository.removeFavoriteCity(cityName = cityName)
    }
}