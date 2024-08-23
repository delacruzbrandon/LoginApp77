package com.clutchit.loginapp77

import android.app.Application
import com.clutchit.loginapp77.di.appModule
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule)
        }
    }
}