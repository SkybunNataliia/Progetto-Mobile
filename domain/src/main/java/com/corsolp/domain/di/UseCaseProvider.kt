package com.corsolp.domain.di

import com.corsolp.domain.usecases.AddFavoriteCityUseCase
import com.corsolp.domain.usecases.AddFavoriteCityUseCaseImpl
import com.corsolp.domain.usecases.FetchCurrentWeatherUseCase
import com.corsolp.domain.usecases.FetchCurrentWeatherUseCaseImpl
import com.corsolp.domain.usecases.FetchFavoritesUseCase
import com.corsolp.domain.usecases.FetchFavoritesUseCaseImpl
import com.corsolp.domain.usecases.FetchForecastUseCase
import com.corsolp.domain.usecases.FetchForecastUseCaseImpl
import com.corsolp.domain.usecases.GeocodeCityUseCase
import com.corsolp.domain.usecases.GeocodeCityUseCaseImpl
import com.corsolp.domain.usecases.RemoveFavoriteCityUseCase
import com.corsolp.domain.usecases.RemoveFavoriteCityUseCaseImpl

object UseCaseProvider {

    lateinit var fetchFavoritesUseCase: FetchFavoritesUseCase
    lateinit var addFavoriteCityUseCase: AddFavoriteCityUseCase
    lateinit var removeFavoriteCityUseCase: RemoveFavoriteCityUseCase
    lateinit var fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase
    lateinit var fetchForecastUseCase: FetchForecastUseCase
    lateinit var geocodeCityUseCase: GeocodeCityUseCase


    fun setup(repositoryProvider: RepositoryProvider) {

        fetchFavoritesUseCase = FetchFavoritesUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        addFavoriteCityUseCase = AddFavoriteCityUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        removeFavoriteCityUseCase = RemoveFavoriteCityUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        fetchCurrentWeatherUseCase = FetchCurrentWeatherUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        fetchForecastUseCase = FetchForecastUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
        geocodeCityUseCase = GeocodeCityUseCaseImpl(
            repository = repositoryProvider.weatherRepository
        )
    }
}