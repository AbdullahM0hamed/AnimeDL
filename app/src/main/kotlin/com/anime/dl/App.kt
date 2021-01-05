package com.anime.dl

import android.app.Application
import android.content.Context

class App : Application() {

    public lateinit var appContext: Context

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
