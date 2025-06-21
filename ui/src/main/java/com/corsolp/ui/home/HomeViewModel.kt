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
    private val fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase,
    private val removeFavoriteCityUseCase: RemoveFavoriteCityUseCase
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

                // Se è la prima volta
                if (favoriteCities.isEmpty()) {
                    try {
                        val defaultCity = City(
                            name = "Rome",
                            country = "IT",
                            lat = 41.9,
                            lon = 12.5,
                        )
                        val weather = fetchCurrentWeatherUseCase(defaultCity.name)
                        _favoritesList.value = listOf(CityWithWeather(defaultCity, weather))
                        Log.i(TAG, "No favorites found, setting default city: $defaultCity")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error setting default", e)
                    }
                    return@collect
                }

                val weatherList = favoriteCities.map { city ->
                    val weather = try {
                        fetchCurrentWeatherUseCase(city.name)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error fetching weather for ${city.name}", e)
                        Weather(name = "", temperature = 0.0, description = "Error")
                    }
                    CityWithWeather(city, weather)
                }
                _favoritesList.value = weatherList
            }
        }
    }

    fun removeFavoriteCity(cityName: String) {
        viewModelScope.launch {
            try {
                removeFavoriteCityUseCase(cityName)
            } catch (e: Exception) {
                Log.e(TAG, "Error removing city $cityName", e)
            }
        }
    }

    data class CityWithWeather(
        val city: City,
        val weather: Weather
    )
}