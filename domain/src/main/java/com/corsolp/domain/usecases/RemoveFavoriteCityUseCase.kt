package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.repository.WeatherRepository

interface RemoveFavoriteCityUseCase {
    suspend fun invoke(city: City): Boolean
}

class RemoveFavoriteCityUseCaseImpl(private val repository: WeatherRepository) : RemoveFavoriteCityUseCase {

    override suspend fun invoke(city: City): Boolean {

        require(city.name.isNotBlank()) { "Il nome della città non può essere vuoto" }

        return repository.removeFavoriteCity(city = city)
    }
}