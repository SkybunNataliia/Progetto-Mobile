package com.corsolp.domain.usecases

import com.corsolp.domain.models.City
import com.corsolp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

interface FetchFavoritesUseCase : () -> Flow<Set<City>>

class FetchFavoritesUseCaseImpl(private val repository: WeatherRepository): FetchFavoritesUseCase {

    override fun invoke(): Flow<Set<City>> {
        return repository.fetchFavorites()
    }

}