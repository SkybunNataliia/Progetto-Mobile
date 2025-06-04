package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.repository.WeatherRepository

interface AddFavoriteCityUseCase {
    suspend fun invoke(cityName: String): Boolean
}

class AddFavoriteCityUseCaseImpl(private val repository: WeatherRepository) : AddFavoriteCityUseCase {

    override suspend fun invoke(cityName: String): Boolean {

        require(cityName.isNotBlank()) { "Il nome della città non può essere vuoto" }

        return repository.addFavoriteCity(cityName = cityName)
    }
}