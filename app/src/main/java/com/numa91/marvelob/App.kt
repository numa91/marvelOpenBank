package com.numa91.marvelob

import android.app.Application
import com.numa91.marvelob.di.appModule
import com.numa91.marvelob.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@App)
            modules(listOf(appModule,networkModule))
        }
    }
}