package com.corsolp.data.remote.models

data class WeatherRemoteModel(
    val name: String,
    val main: Main,
    val weather: List<WeatherDescription>
)

data class Main(
    val temp: Float
)

data class WeatherDescription(
    val main: String,
    val description: String
)
