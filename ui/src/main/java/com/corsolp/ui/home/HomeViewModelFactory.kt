package com.corsolp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.di.UseCaseProvider

class HomeViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                fetchFavoritesUseCase = UseCaseProvider.fetchFavoritesUseCase,
                fetchCurrentWeatherUseCase = UseCaseProvider.fetchCurrentWeatherUseCase,
                removeFavoriteCityUseCase = UseCaseProvider.removeFavoriteCityUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}