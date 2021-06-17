package com.example.koinwithapi.di

import android.app.Application
import com.example.koinwithapi.data.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@BaseApp)
            modules(appModule)
        }
    }
}