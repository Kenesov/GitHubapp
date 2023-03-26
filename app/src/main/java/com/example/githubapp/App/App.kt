package com.example.githubapp.App

import android.app.Application
import com.example.githubapp.di.appModule
import com.example.githubapp.di.viewModelModule
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin{
            modules(listOf(appModule, viewModelModule))
        }

    }


}