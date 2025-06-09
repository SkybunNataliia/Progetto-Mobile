package com.corsolp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corsolp.domain.models.City
import com.corsolp.domain.models.Weather
import com.corsolp.domain.usecases.FetchCurrentWeatherUseCase
import com.corsolp.domain.usecases.FetchFavoritesUseCase
import com.corsolp.domain.usecases.RemoveFavoriteCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (
    private val fetchFavoritesUseCase: FetchFavoritesUseCase,
    private val fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase
): ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _favoritesList = MutableStateFlow<List<CityWithWeather>>(emptyList())
    val favoritesList: StateFlow<List<CityWithWeather>> = _favoritesList

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            fetchFavoritesUseCase().collect { favoriteCities ->
                val weatherList = favoriteCities.map { city ->
                    val weather = try {
                        fetchCurrentWeatherUseCase(city.name)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error fetching weather for ${city.name}", e)
                        CityWithWeather(city, Weather(name = "", temperature = 0.0, description = "Error"))
                    }
                    CityWithWeather(city, weather)
                }
                _favoritesList.value = weatherList
            }
        }
    }

    data class CityWithWeather(
        val city: City,
        val weather: Any
    )
}