package com.corsolp.domain.models

data class Forecast(
    val items: List<ForecastItem>
)

data class ForecastItem(
    val dateTime: String,
    val weather: String,
    val temperature: Double,
    val tempMin: Double,
    val tempMax: Double,
    val description: String
)