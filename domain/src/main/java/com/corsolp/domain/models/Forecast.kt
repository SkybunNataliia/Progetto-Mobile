package com.corsolp.domain.models

data class Forecast(
    val city: City,
    val items: List<ForecastItem>
)

data class ForecastItem(
    val timestamp: Long,
    val dateTime: String,
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,
    val weather: Weather
)