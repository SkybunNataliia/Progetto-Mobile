package com.corsolp.data.di

import android.content.Context
import com.corsolp.data.remote.RetrofitClient
import com.corsolp.data.repository.WeatherRepositoryImpl
import com.corsolp.domain.di.RepositoryProvider
import com.corsolp.domain.repository.WeatherRepository

class RepositoryProviderImpl(private val context: Context): RepositoryProvider {

    private val retrofitClient = RetrofitClient()

    override val weatherRepository: WeatherRepository = WeatherRepositoryImpl(
        context = context,
        weatherApi = retrofitClient.weatherApi
    )
}
