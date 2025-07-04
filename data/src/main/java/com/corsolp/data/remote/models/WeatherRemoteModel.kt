package com.corsolp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherRemoteModel(
    val main: Main,
    val weather: List<WeatherDescription>
)

data class Main(
    @Json(name = "temp") val temp: Float
)

data class WeatherDescription(
    @Json(name = "main") val name: String,
    @Json(name = "description") val description: String
)
