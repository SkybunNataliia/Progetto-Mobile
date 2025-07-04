package com.corsolp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastRemoteModel(
    val list: List<ForecastRemoteItem>
)

@JsonClass(generateAdapter = true)
data class ForecastRemoteItem(
    @Json(name = "dt_txt") val date: String,
    val main: Mains,
    val weather: List<WeatherDescription>
)

@JsonClass(generateAdapter = true)
data class Mains(
    @Json(name = "temp") val temp: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float
)
