package com.corsolp.domain.di

import com.corsolp.domain.repository.WeatherRepository

interface RepositoryProvider {
    val weatherRepository: WeatherRepository
}