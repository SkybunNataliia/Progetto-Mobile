package com.corsolp.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corsolp.domain.di.UseCaseProvider

class ForecastViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ForecastViewModel(
                fetchForecastUseCase = UseCaseProvider.fetchForecastUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}