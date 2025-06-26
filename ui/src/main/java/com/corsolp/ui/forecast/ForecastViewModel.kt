package com.corsolp.ui.forecast

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.ForecastItem
import com.corsolp.domain.usecases.FetchForecastUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val fetchForecastUseCase: FetchForecastUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "ForecastViewModel"
    }

    private val _forecast = MutableStateFlow<List<ForecastItem>>(emptyList())
    val forecast: StateFlow<List<ForecastItem>> = _forecast

    fun loadForecast(cityName: String) {
        viewModelScope.launch {
            try {
                val result = fetchForecastUseCase(cityName)
                _forecast.value = result.items
                Log.d(TAG, "Forecast list for $cityName: ${result.items}")
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching forecast for $cityName", e)
            }
        }
    }
}