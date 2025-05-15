package com.corsolp.appmeteo

import android.app.Application
import com.corsolp.data.di.RepositoryProviderImpl
import com.corsolp.domain.di.UseCaseProvider

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        UseCaseProvider.setup(
            repositoryProvider = RepositoryProviderImpl(context = this.applicationContext)
        )
    }
}