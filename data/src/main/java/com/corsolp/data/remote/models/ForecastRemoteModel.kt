package com.corsolp.data.remote.models

data class ForecastRemoteModel(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt_txt: String,
    val main: Mains,
    val weather: List<WeatherDescription>
)

data class Mains(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float
)
