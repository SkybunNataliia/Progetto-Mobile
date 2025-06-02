package com.corsolp.appmeteo

import android.app.Application
import android.content.Context
import com.corsolp.data.di.RepositoryProviderImpl
import com.corsolp.domain.di.UseCaseProvider

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        UseCaseProvider.setup(
            repositoryProvider = RepositoryProviderImpl(context = this.applicationContext)
        )

        val prefs = getSharedPreferences("favorites", Context.MODE_PRIVATE)

        if (!prefs.contains("city")) {
            prefs.edit().putString("city", "Rome").apply()
        }
    }
}