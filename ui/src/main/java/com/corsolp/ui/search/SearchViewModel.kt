package com.corsolp.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.City
import com.corsolp.domain.models.Weather
import com.corsolp.domain.usecases.AddFavoriteCityUseCase
import com.corsolp.domain.usecases.FetchCurrentWeatherUseCase
import com.corsolp.domain.usecases.GeocodeCityUseCase
import com.corsolp.ui.home.HomeViewModel
import com.corsolp.ui.home.HomeViewModel.Companion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val geocodeCityUseCase: GeocodeCityUseCase,
    private val fetchWeatherUseCase: FetchCurrentWeatherUseCase,
    private val addFavoriteCityUseCase: AddFavoriteCityUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "SearchViewModel"
    }

    private val _weather = MutableStateFlow<CityWithWeather?>(null)
    val weather: StateFlow<CityWithWeather?> = _weather

    private val _lastCitySearched = MutableStateFlow<String?>(null)
    val lastCitySearched: StateFlow<String?> = _lastCitySearched

    fun searchCity(cityName: String) {
        viewModelScope.launch {
            try {
                _lastCitySearched.value = cityName
                val city = geocodeCityUseCase(cityName)
                val weather = city?.let { fetchWeatherUseCase(it.name) }
                _weather.value = CityWithWeather(city, weather)
            } catch (e: Exception) {
                Log.e(TAG, "Error searching weather for $cityName", e)
                _weather.value = null
            }
        }
    }

    fun addToFavorites() {
        viewModelScope.launch {
            try {
                _lastCitySearched.value?.let { addFavoriteCityUseCase(it) }
            } catch (e: Exception) {
                Log.e(TAG, "Error adding city", e)
            }
        }
    }

    data class CityWithWeather(val city: City?, val weather: Weather?)
}