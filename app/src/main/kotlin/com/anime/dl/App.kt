package com.anime.dl

import android.app.Application
import android.content.Context

class App : Application() {

    lateinit var appContext: Context

    override fun onCreate() {
        super.onCreate()
        App.appContext = applicationContext
    }
}
