package com.corsolp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.di.UseCaseProvider

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(
                geocodeCityUseCase = UseCaseProvider.geocodeCityUseCase,
                fetchWeatherUseCase = UseCaseProvider.fetchCurrentWeatherUseCase,
                addFavoriteCityUseCase = UseCaseProvider.addFavoriteCityUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}