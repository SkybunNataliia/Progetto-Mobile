package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.repository.WeatherRepository

interface AddFavoriteCityUseCase {
    suspend fun invoke(city: City): Boolean
}

class AddFavoriteCityUseCaseImpl(private val repository: WeatherRepository) : AddFavoriteCityUseCase {

    override suspend fun invoke(city: City): Boolean {

        require(city.name.isNotBlank()) { "Il nome della città non può essere vuoto" }

        return repository.addFavoriteCity(city = city)
    }
}